package service;


import model.Data;

import java.util.LinkedList;
import java.util.List;

/* Филиппов А.В. 21.05.2020 Комментарий не удалять.
 Не работает. Итератор должен уметь ходить по пустым группам.
*/

// fixed



public class DataDemo {
    public static List<Integer> getAll(Data data) {
        List<Integer> all = new LinkedList<>();
        
        for (Integer e: data)
            all.add(e);
        
        return all;
    }
}
