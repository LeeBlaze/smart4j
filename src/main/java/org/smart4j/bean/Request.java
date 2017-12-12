package org.smart4j.bean;

/**
 * Create by Lee on 2017/12/12
 */
public class Request {

    private String requestMethod;

    private String requestPath;

    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    @Override
    public int hashCode() {
        return requestMethod.hashCode() ^ requestPath.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Request request = (Request)obj;
        return request.requestPath.equals(requestPath) && request.requestMethod.equals(requestMethod);
    }
}
