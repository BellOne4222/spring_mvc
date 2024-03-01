package springmvc.itemservice.domain.item;

import lombok.Data;


@Data // 롬복의 @Data 어노테이션 추가
public class Item {
	private Long id; // id 필드 추가
	private String itemName; // 상품명
	private Integer price; // 가격
	private Integer quantity; // 수량

	// 기본 생성자
	public Item() {
	}

	// 생성자
	public Item(String itemName, Integer price, Integer quantity) {
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
}
