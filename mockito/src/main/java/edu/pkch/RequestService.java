package edu.pkch;

public class RequestService {
    private final RequestClient requestClient;

    public RequestService(RequestClient requestClient) {
        this.requestClient = requestClient;
    }

    public void requestAllStatus(String name) {
        requestClient.request(Request.ofActive(name));
        requestClient.request(Request.ofWithdraw(name));
    }

    public void requestActive(String name) {
        requestClient.request(Request.ofActive(name));
    }

    public void requestWithdraw(String name) {
        requestClient.request(Request.ofWithdraw(name));
    }
}
