package com.example.library.aop;

import com.example.library.model.BorrowTicket;
import com.example.library.model.Book;
import com.example.library.service.VisitorCounter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LibraryLoggingAspect {

    private final VisitorCounter counter;

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void anyController() {}

    @Before("anyController()")
    public void countVisitors(JoinPoint jp) {
        long n = counter.increaseAndGet();
        log.info("-------------------- VISIT --------------------");
        log.info("Controller: {}.{}() | Total visitors={}",
                jp.getSignature().getDeclaringType().getSimpleName(),
                jp.getSignature().getName(), n);
        log.info("------------------------------------------------");
    }

    @AfterReturning(
            pointcut = "execution(* com.example.library.service.LibraryService.borrow(..))",
            returning = "ticket")
    public void logBorrow(BorrowTicket ticket) {
        Book b = ticket.getBook();
        log.info("-------------------- BORROW -------------------");
        log.info("Book ID={} | Title='{}' | New quantity={} | Ticket code={}",
                b.getId(), b.getTitle(), b.getQuantity(), ticket.getCode());
        log.info("------------------------------------------------");
    }

    @AfterReturning(
            pointcut = "execution(* com.example.library.service.LibraryService.returnByCode(..))",
            returning = "book")
    public void logReturn(Book book) {
        log.info("-------------------- RETURN -------------------");
        log.info("Book ID={} | Title='{}' | New quantity={}",
                book.getId(), book.getTitle(), book.getQuantity());
        log.info("------------------------------------------------");
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.library.service.LibraryService.*(..))",
            throwing = "ex")
    public void logBizError(RuntimeException ex) {
        log.warn("-------------------- ERROR --------------------");
        log.warn("Type={} | Message={}", ex.getClass().getSimpleName(), ex.getMessage());
        log.warn("------------------------------------------------");
    }
}
