package com.example.book.mapper;

import com.example.book.dto.BookDTO;
import com.example.book.dto.MessageDTO;
import com.example.book.entity.Book;
import com.example.book.entity.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {


    BookDTO toDTO(Book book);
    Book toEntity(BookDTO bookDTO);
    MessageDTO toDTO(Message message);
    Message toEntity(MessageDTO messageDTO);
}
