package me.egg_nuts.user_manager.cache;

import me.egg_nuts.user_manager.defined.CacheCommand;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

public abstract class CommonCache<T> {
    protected final ConcurrentHashMap<String, Future<T>> cache = new ConcurrentHashMap<>();

    protected abstract Optional<T> cacheAction(String uniqueKey,Optional<T> cacheDto,Future<T> cacheDtoFuture);
    protected abstract Optional<T> getAction(String uniqueKey,Future<T> cacheDtoFuture);
    protected abstract Optional<T> deleteAction(String uniqueKey,Future<T> cacheDtoFuture);
 ;

    protected T getCacheDto(Optional<T> cacheDto){
        return cacheDto.get();
    }
    public final Optional<T> computed(String uniqueKey, Optional<T> cacheDto, CacheCommand cacheCommand){
        while (true){
            Future<T> cacheDtoFuture = cache.get(uniqueKey);

            if(cacheCommand==CacheCommand.CACHE){
                return cacheAction(uniqueKey,cacheDto,cacheDtoFuture);
            }else if(cacheCommand==CacheCommand.GET){
                return getAction(uniqueKey,cacheDtoFuture);
            }else{
                return deleteAction(uniqueKey,cacheDtoFuture);
            }
        }
    }
}
