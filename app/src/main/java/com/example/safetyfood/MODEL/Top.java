package com.example.safetyfood.MODEL;

public class Top {
    int ProductId,sum;

    public Top(int productId, int sum) {
        ProductId = productId;
        this.sum = sum;
    }

    public Top() {
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
