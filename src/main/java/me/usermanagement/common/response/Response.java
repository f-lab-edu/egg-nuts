package me.usermanagement.common.response;

import me.usermanagement.common.response.messages.MessageCommon;
import me.usermanagement.common.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class Response implements Serializable {
    private int statusCode;
    private MessageCommon message;
    private Object result;
    private String responseText;

    private Response(ResponseBuilder errorResponseBuilder) {
        this.statusCode = errorResponseBuilder.statusCode;
        this.message = errorResponseBuilder.message;
        this.responseText = errorResponseBuilder.responseText;
        this.result = errorResponseBuilder.result;
    }

    public static class ResponseBuilder implements Builder<Response> {
        private int statusCode;
        private MessageCommon message;
        private Object result = new Object();
        private String responseText;

        public ResponseBuilder() {
        }

        public ResponseBuilder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ResponseBuilder message(MessageCommon message) {
            this.message = message;
            return this;
        }

        public ResponseBuilder responseText(String responseText) {
            this.responseText = responseText;
            return this;
        }

        public ResponseBuilder result(Object result) {
            this.result = result;
            return this;
        }

        @Override
        public Response toCommand() {
            return new Response(this);
        }
    }

}
