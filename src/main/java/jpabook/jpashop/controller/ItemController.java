package jpabook.jpashop.controller;

import jpabook.jpashop.controller.form.BookForm;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    @GetMapping("/items/new")
    public String createForm(@ModelAttribute BookForm form) {
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(@Valid @ModelAttribute BookForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "items/createItemForm";
        }

        /**
         * 빌더 패턴 적용해보기
         */
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);

        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, @ModelAttribute BookForm form) {
        Book item = (Book) itemService.findOne(itemId);

        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@ModelAttribute BookForm form) {

        itemService.updateItem(form.getId(), form.getName(), form.getPrice(),form.getStockQuantity());
        return "redirect:/items";
    }
}
