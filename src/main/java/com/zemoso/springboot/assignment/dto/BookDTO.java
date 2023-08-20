package com.zemoso.springboot.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;
    private String bookName;
    private String bookAuthor;
    private String bookPrice;
    //this is defined because we only want this info to send to the client instead of all info
}
