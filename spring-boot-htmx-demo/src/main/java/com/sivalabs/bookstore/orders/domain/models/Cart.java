package com.sivalabs.bookstore.orders.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Cart {
    private List<LineItem> items = new ArrayList<>();

    public void addItem(LineItem item) {
        items.stream().filter(i -> i.getCode().equals(item.getCode()))
                .findFirst()
                .ifPresentOrElse(i -> i.setQuantity(i.getQuantity() + item.getQuantity()), () -> items.add(item));
    }

    public int getItemCount() {
        return items.stream().mapToInt(LineItem::getQuantity).sum();
    }

    public BigDecimal getTotalAmount() {
        return items.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void removeItem(String code) {
        items.removeIf(i -> i.getCode().equals(code));
    }

    public void updateItemQuantity(String code, int quantity) {
        if(quantity <= 0) {
            removeItem(code);
            return;
        }
        items.stream().filter(i -> i.getCode().equals(code))
                .findFirst()
                .ifPresent(i -> i.setQuantity(quantity));
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LineItem {
        private String code;
        private String name;
        private BigDecimal price;
        private int quantity;
    }
}
