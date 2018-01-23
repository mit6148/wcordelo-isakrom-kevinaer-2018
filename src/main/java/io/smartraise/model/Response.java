package io.smartraise.model;

public class Response {

    private final String requestId;
    private final String adminResponseId;
    private final boolean response;

    public Response() {
        this.requestId = "";
        this.adminResponseId = "";
        this.response = false;
    }

    public Response(String requestId, String adminResponseId, boolean response) {
        this.requestId = requestId;
        this.adminResponseId = adminResponseId;
        this.response = response;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getAdminResponseId() {
        return adminResponseId;
    }

    public boolean isResponse() {
        return response;
    }
}
