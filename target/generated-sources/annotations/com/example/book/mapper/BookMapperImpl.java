package com.example.book.mapper;

import com.example.book.dto.BookDTO;
import com.example.book.dto.MessageDTO;
import com.example.book.entity.Book;
import com.example.book.entity.Message;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-09T06:36:40+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.14 (JetBrains s.r.o.)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO toDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId( book.getId() );
        bookDTO.setTitle( book.getTitle() );
        bookDTO.setType( book.getType() );
        bookDTO.setPublicationDate( book.getPublicationDate() );

        return bookDTO;
    }

    @Override
    public Book toEntity(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDTO.getId() );
        book.setTitle( bookDTO.getTitle() );
        book.setType( bookDTO.getType() );
        book.setPublicationDate( bookDTO.getPublicationDate() );

        return book;
    }

    @Override
    public MessageDTO toDTO(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setId_author( message.getId_author() );
        messageDTO.setAuthor( message.getAuthor() );
        messageDTO.setMessage( message.getMessage() );

        return messageDTO;
    }

    @Override
    public Message toEntity(MessageDTO messageDTO) {
        if ( messageDTO == null ) {
            return null;
        }

        Message message = new Message();

        message.setId_author( messageDTO.getId_author() );
        message.setAuthor( messageDTO.getAuthor() );
        message.setMessage( messageDTO.getMessage() );

        return message;
    }
}
