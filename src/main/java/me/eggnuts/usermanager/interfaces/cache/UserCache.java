package me.eggnuts.usermanager.interfaces.cache;

import me.eggnuts.usermanager.domain.model.user.UserDto;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@Component
public class UserCache extends CommonCache<UserDto> {

    @Override
    protected Optional<UserDto> cacheAction(String userId, Optional<UserDto> userDto, Future<UserDto> userDtoFuture) {
        try {
            boolean userDtoExist = userDto.isPresent();
            if (userDtoExist) {
                Callable<UserDto> callUserDto = () -> getCacheDto(userDto);
                FutureTask<UserDto> futureTask = new FutureTask<>(callUserDto);
                userDtoFuture = super.cache.putIfAbsent(userId, futureTask);
                if (userDtoFuture == null) {
                    futureTask.run();
                    futureTask.get();
                }
                return Optional.empty();
            } else {
                return Optional.ofNullable(userDtoFuture.get());
            }
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    protected Optional<UserDto> getAction(String userId, Future<UserDto> userDtoFuture) {
        try {
            return Optional.ofNullable(userDtoFuture.get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    protected Optional<UserDto> deleteAction(String userId, Future<UserDto> userDtoFuture) {
        super.cache.remove(userId);
        return Optional.empty();
    }


}
