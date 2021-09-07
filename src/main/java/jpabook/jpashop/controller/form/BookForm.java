package jpabook.jpashop.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter
public class BookForm {

    private Long id;

    @NotEmpty(message = "이름은 필수 입니다.")
    @Size(min = 2, max = 10, message = "이름은 2자 이상 10자 이하로 입력해주세요.")
    private String name;

    @NotNull(message = "가격은 필수 입니다.")
    @Min(value = 1000, message = "가격은 최소 1,000원 입니다.")
    @Max(value = 1000000, message = "가격은 최대 1,000,000원 입니다.")
    private int price;

    @NotNull(message = "수량은 필수 입니다.")
    @Min(value = 1, message = "수량은 최소 1개 이상 이여야 합니다.")
    @Max(value = 999, message = "수량은 최대 1000개 미만 이여야 입니다.")
    private int stockQuantity;

    @NotEmpty(message = "작가 이름은 필수 입니다.")
    @Size(min = 2, max = 10, message = "작가 이름은 2자 이상 10자 이하로 입력해주세요.")
    private String author;

    @NotEmpty(message = "ISBN은 필수 입니다.")
    private String isbn;
}
