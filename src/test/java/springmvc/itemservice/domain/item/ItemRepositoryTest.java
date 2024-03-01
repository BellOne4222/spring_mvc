package springmvc.itemservice.domain.item;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

	ItemRepository itemRepository = new ItemRepository(); // 테스트할 상품 저장소

	@AfterEach
	void afterEach() {
		itemRepository.clearStore(); // 테스트 종료 후 저장소 클리어
	}

	// 상품 저장 테스트
	@Test
	void save() {
		// given
		Item item = new Item("itemA", 10000, 10); // 상품 생성
		// when
		Item savedItem = itemRepository.save(item); // 상품 저장
		// then
		Item findItem = itemRepository.findById(item.getId()); // 저장된 상품 조회
		assertThat(findItem).isEqualTo(savedItem); // 저장된 상품과 조회한 상품이 같은지 확인
	}

	// 전체 상품 조회 테스트
	@Test
	void findAll() {
		// given
		Item item1 = new Item("item1", 10000, 10); // 상품1 생성
		Item item2 = new Item("item2", 20000, 20); // 상품2 생성
		itemRepository.save(item1); // 상품1 저장
		itemRepository.save(item2); // 상품2 저장

		// when
		List<Item> result = itemRepository.findAll(); // 전체 상품 조회

		// then
		assertThat(result.size()).isEqualTo(2); // 조회된 상품 개수가 2개인지 확인
	}

	// 상품 수정 테스트
	@Test
	void updateItem() {
		// given
		Item item = new Item("item1", 10000, 10); // 상품 생성
		Item savedItem = itemRepository.save(item); // 상품 저장
		Long itemId = savedItem.getId(); // 저장된 상품 id

		// when
		Item updateParam = new Item("item2", 20000, 30); // 수정할 상품 생성
		itemRepository.update(itemId, updateParam); // 상품 수정

		// then
		Item findItem = itemRepository.findById(itemId); // 수정된 상품 조회
		assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName()); // 수정된 상품명 확인
		assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice()); // 수정된 가격 확인
		assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity()); // 수정된 수량 확인
	}


}