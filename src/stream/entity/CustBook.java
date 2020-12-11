package stream.entity;

import java.util.List;

/**
 * @ClassName CustBook
 * @Description //TODO
 * @Author ccy
 * @Date 2020/12/11 10:05
 * @Version 1.0
 **/
public class CustBook {

    public CustBook(List<String> bookName){
        setBookName(bookName);
    }

    private List<String> bookName;

    public List<String> getBookName() {
        return bookName;
    }

    public void setBookName(List<String> bookName) {
        this.bookName = bookName;
    }

}
