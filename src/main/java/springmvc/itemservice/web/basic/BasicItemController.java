package springmvc.itemservice.web.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import springmvc.itemservice.domain.item.Item;
import springmvc.itemservice.domain.item.ItemRepository;

@Controller // 스프링 빈으로 등록
@RequestMapping("/basic/items") // 요청 URL 매핑
@RequiredArgsConstructor // final이 붙은 필드의 생성자를 생성해준다.
public class BasicItemController {

	private final ItemRepository itemRepository; // 상품 저장소

	// 상품 목록 조회
	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll(); // 전체 상품 조회
		model.addAttribute("items", items); // 상품 목록을 모델에 담아서 뷰에 전달
		return "basic/items"; // 뷰 이름 반환
	}

	// 상품 상세 조회
	@GetMapping("/{itemId}") // itemId는 @PathVariable로 조회
	public String item(@PathVariable(name = "itemId") Long itemId, Model model) { // itemId로 상품 조회
		Item item = itemRepository.findById(itemId); // 상품 조회
		model.addAttribute("item", item); // 상품을 모델에 담아서 뷰에 전달
		return "basic/item"; // 뷰 이름 반환
	}

	// 상품 등록 폼
	@GetMapping("/add")
	public String addForm() {
		return "basic/addForm"; // 뷰 이름 반환
	}

	// 상품 등록 처리
	@PostMapping("/add") // POST 요청을 처리
	public String addItem(Item item, RedirectAttributes redirectAttributes) { // 상품 정보를 받아서 처리
		Item savedItem = itemRepository.save(item); // 상품 저장
		redirectAttributes.addAttribute("itemId", savedItem.getId()); // 상품 ID를 리다이렉트 URL에 추가
		redirectAttributes.addAttribute("status", true); // 상품 등록 성공 여부를 리다이렉트 URL에 추가
		return "redirect:/basic/items/{itemId}"; // 뷰 이름 반환
	}

	// 상품 수정 폼
	@GetMapping("/{itemId}/edit") // itemId는 @PathVariable로 조회
	public String editForm(@PathVariable("itemId") Long itemId, Model model) { // itemId로 상품 조회
		Item item = itemRepository.findById(itemId); // 상품 조회
		model.addAttribute("item", item); // 상품을 모델에 담아서 뷰에 전달
		return "basic/editForm"; // 뷰 이름 반환
	}

	// 상품 수정 처리
	@PostMapping("/{itemId}/edit") // POST 요청을 처리
	public String edit(@PathVariable("itemId") Long itemId, @ModelAttribute Item item) { // itemId로 상품 조회
		itemRepository.update(itemId, item); // 상품 수정
		return "redirect:/basic/items/{itemId}"; // 뷰 이름 반환
	}



	// 테스트용 데이터 추가
	@PostConstruct // 의존관계 주입이 이루어진 후 초기화를 수행하기 위한 메서드
	public void init() {
		itemRepository.save(new Item("itemA", 10000, 10)); // 상품 저장
		itemRepository.save(new Item("itemB", 20000, 20)); // 상품 저장
	}

}


