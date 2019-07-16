package ru.otus.spring.shell;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.TableModel;
import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Map;

public class BeanTableModelHelper {

    public static <T> TableModel getTableModel(List<T> objects) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(objects.get(0),new TypeReference<Map<String, Object>>() {});
        Object[][] data = new Object[objects.size()+1][map.size()];
        int k = 0;
        for (Map.Entry entry : map.entrySet()) {
            data[0][k++] = entry.getKey();
        }
        int j = 1;
        for(Object obj: objects){
            map = objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});
            int i = 0;
            for (Map.Entry entry : map.entrySet()) {
                data[j][i++] = entry.getValue();
            }
            j++;
        }
        return new ArrayTableModel(data);
    }
}
