package springmvc.itemservice.domain.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

// 상품 저장소
@Repository // 스프링 빈으로 등록
public class ItemRepository {

	private static final Map<Long, Item> store = new HashMap<>(); // 상품 저장용 맵, 싱글톤으로 생성하기 때문에 동시 접근 시에는 ConcurrentHashMap 사용
	private static long sequence = 0L; // 상품 id 생성용

	// 상품 저장
	public Item save(Item item) {
		item.setId(++sequence); // 상품 id 세팅
		store.put(item.getId(), item); // 상품 저장
		return item; // 저장된 상품 반환
	}

	// id로 상품 찾기
	public Item findById(Long id) {
		return store.get(id); // id로 상품 조회
	}

	// 전체 상품 조회
	public List<Item> findAll() {
		return new ArrayList<>(store.values()); // 전체 상품 반환
	}

	// 상품 수정
	public void update(Long itemId, Item updateParam) {
		Item findItem = findById(itemId); // id로 상품 조회
		findItem.setItemName(updateParam.getItemName()); // 상품명 수정
		findItem.setPrice(updateParam.getPrice()); // 가격 수정
		findItem.setQuantity(updateParam.getQuantity()); // 수량 수정
	}

	public void clearStore() {
		store.clear(); // 상품 저장소 클리어
	}

}
