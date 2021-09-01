package com.summit.comparator;
import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author: jeerper
 * @date: 2021-09-01 11:30:05
 */

public class PinyinComparator implements Comparator<String> {

    /**
     * 生成排序器
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Comparator<T>comparing(Function<? super T,String> keyExtractor){
        Objects.requireNonNull(keyExtractor);
        PinyinComparator pinyinComparator = new PinyinComparator();
        return (o1,o2)->pinyinComparator.compare(keyExtractor.apply(o1),keyExtractor.apply(o2));
    }

    /**
     * 中文字符串 按拼音排序
     */
    @Override
    public int compare(String o1, String o2) {
        for(int i = 0; i < o1.length() && i < o2.length(); i++){
            int codePoint1 = o1.charAt(i);
            int codePoint2 = o2.charAt(i);
            //是否在增补字符范围内
          boolean b = Character.isSupplementaryCodePoint(codePoint1) || Character.isSupplementaryCodePoint(codePoint2);
          if (b){
              i++;
          }
          if (codePoint1 != codePoint2){
              if (b){
                  return codePoint1 - codePoint2;
              }
              String pinyin1 = pinyin((char) codePoint1);
              String pinyin2 = pinyin((char) codePoint2);
              if (pinyin1 != null && pinyin2!= null){ //两个字符都是汉字
                  if (!pinyin1.equals(pinyin2)){
                      return pinyin1.compareTo(pinyin2);
                  }
              }else {
                  return codePoint1 - codePoint2;
              }
          }

        }
        return o1.length()-o2.length();
    }


    /**
     * 字符的拼音，多音字就得到第一个拼音。不是汉字，就return null。
     * @param c
     * @return
     */
    private String pinyin(char c){
        String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(c);
        if (pinyin == null){
            return null;
        }
        return pinyin[0];
    }
}
