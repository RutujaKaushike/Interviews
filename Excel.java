import java.util.*;

public class Excel {

    Map<String, String> map1 = new HashMap<>();
    Map<String, List<String>> map2 = new HashMap<>();
    Map<String, String[]> map3 = new HashMap<>();

    public Excel(){

    }

    public void update(String cellKey, String value){
        String[] strs = value.split(" ");

        if(strs.length == 1){
            map1.put(cellKey, value);
            if(map2.containsKey(cellKey)){
                for(String s: map2.get(cellKey)){
                    map1.put(s, calculateValue(map3.get(s)));
                }
            }
        } else if (strs.length == 3){
            map3.put(cellKey, strs);

            if(!map2.containsKey(strs[0])){
                map2.put(strs[0], new ArrayList<>());
            }

            if(!map2.containsKey(strs[2])){
                map2.put(strs[2], new ArrayList<>());
            }

            List<String> l1 = map2.get(strs[0]);

            if(!l1.contains(cellKey)){
                l1.add(cellKey);
                map2.put(strs[0], l1);
//                System.out.println(map2.get(strs[0]));
            }

            List<String> l2 = map2.get(strs[2]);
            if(!l2.contains(cellKey)){
                l2.add(cellKey);
                map2.put(strs[2], l2);
//                System.out.println(map2.get(strs[0]));
            }

            String s1 = calculateValue(map3.get(cellKey));
//            System.out.println(s1);
            map1.put(cellKey, s1);
        }
    }

    public Map<String, String> getAllCellValues(){
        return map1;
    }

    public String getCellValue(String cellKey){

        if(map1.containsKey(cellKey)){
            return map1.get(cellKey);
        }

        return null;
    }

    private String calculateValue(String[] strs){
        Integer a;
        if(!map1.containsKey(strs[0])){
            a = Integer.parseInt(strs[0]);
        } else {
            a = Integer.valueOf(map1.get(strs[0]));
        }

        Integer b = Integer.valueOf(map1.get(strs[2]));

        if((strs[1]).equals("+")){
            return String.valueOf(a+b);
        } else if((strs[1]).equals("-")){
            return String.valueOf(a-b);
        } else if((strs[1]).equals("*")){
            return String.valueOf(a*b);
        } else if((strs[1]).equals("/")){
            try {
                return String.valueOf(a/b);
            } catch (Exception e){
                return "/ by zero";
            }

        } else {
            return null;
        }

    }


    static public void main( String args[] ) {

        Excel e1 = new Excel();
        e1.update("A1", "2");
        e1.update("A2", "3");
        System.out.println(e1.getCellValue("A1")); // 2
        System.out.println(e1.getCellValue("A2")); // 3
        e1.update("B1", "A1 + A2");
        System.out.println(e1.getCellValue("B1")); // 5
        e1.update("B2", "5 + A2");
        System.out.println(e1.getCellValue("B2")); // 8
        e1.update("A2", "6");
        System.out.println(e1.getCellValue("A2")); // 6
        System.out.println(e1.getCellValue("B1")); // 8
        System.out.println(e1.getCellValue("B2")); // 11
        System.out.println(e1.getAllCellValues());
        e1.update("A3", "10");
        System.out.println(e1.getCellValue("A3")); // 10
        e1.update("A2", "A1 * A3");
        System.out.println(e1.getCellValue("A2")); // 20
        System.out.println(e1.getAllCellValues());
        e1.update("A4", "0");
        e1.update("B3", "A3 / A4");
        System.out.println(e1.getCellValue("A4")); // 0
        System.out.println(e1.getCellValue("B3")); // Divide By Zero Error
        System.out.println(e1.getAllCellValues());
    }
}
