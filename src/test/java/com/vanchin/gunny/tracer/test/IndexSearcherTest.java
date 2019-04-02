package com.vanchin.gunny.tracer.test;

import com.vanchin.gunny.tracer.index.Direction;
import com.vanchin.gunny.tracer.index.Index;
import com.vanchin.gunny.tracer.index.Target;
import com.vanchin.gunny.tracer.search.IndexSearcher;
import com.vanchin.gunny.tracer.search.SearcherFactory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

/**
 * @author vanchin
 * @date 2019/3/20 11:29
 */
public class IndexSearcherTest {
    @Test
    public void testSearch() {
        Pair<List<Target>, List<Direction<Target>>> testData = getTestData();
        Index index = new Index(PersonEnum.types(), testData.getKey(), testData.getValue());
        IndexSearcher searcher = SearcherFactory.newSearcher(index);

        List<Target> list1 = searcher.search(getParam1());
        print(list1);
        List<Target> list1_1 = searcher.search(PersonEnum.types(),getParam1());
        print(list1_1);

        List<Target> list2 = searcher.search(getParam2());
        print(list2);
        List<Target> list2_1 = searcher.search(PersonEnum.types(),getParam2());
        print(list2_1);

        List<Target> list3 = searcher.search(getParam3());
        print(list3);
        List<Target> list3_1 = searcher.search(PersonEnum.types(),getParam3());
        print(list3_1);
    }

    private List<Direction<Target>> getParam1() {

        Direction<Target> d1 = new Direction<Target>(PersonEnum.AGE.getType(), "23");


        List<Direction<Target>> directionList = new ArrayList<Direction<Target>>();
        directionList.add(d1);

        return directionList;
    }

    private List<Direction<Target>> getParam2() {
        //test
        Direction<Target> d1 = new Direction<Target>(PersonEnum.AGE.getType(), "24");
        Direction<Target> d2 = new Direction<Target>(PersonEnum.HANDSOME.getType(), "1");
        Direction<Target> d3 = new Direction<Target>(PersonEnum.HEIGHT.getType(), "183");
        Direction<Target> d4 = new Direction<Target>(PersonEnum.WEALTH.getType(), "100W");

        List<Direction<Target>> directionList = new ArrayList<Direction<Target>>();
        directionList.add(d1);
        directionList.add(d2);
        directionList.add(d3);
        directionList.add(d4);


        return directionList;
    }

    private List<Direction<Target>> getParam3() {

        Direction<Target> d1 = new Direction<Target>(PersonEnum.WEALTH.getType(), "100W");

        List<Direction<Target>> directionList = new ArrayList<Direction<Target>>();
        directionList.add(d1);

        return directionList;
    }

    private void print(List<Target> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("result is empty");
            return;
        }
        for (Target t : list) {
            System.out.println(t.toString());
        }
    }

    private Pair<List<Target>, List<Direction<Target>>> getTestData() {
        Tag tag1 = new Tag(1, "小鲜肉");
        Tag tag2 = new Tag(2, "高富帅");
        Tag tag3 = new Tag(3, "高大魁梧");
        Tag tag4 = new Tag(4, "富翁");

        List<Tag> tagList = new ArrayList<Tag>();
        tagList.add(tag1);
        tagList.add(tag2);
        tagList.add(tag3);
        tagList.add(tag4);

        Direction<Tag> d1 = new Direction<Tag>(tag1, PersonEnum.AGE.getType(), "23");
        Direction<Tag> d2 = new Direction<Tag>(tag1, PersonEnum.AGE.getType(), "20");
        Direction<Tag> d3 = new Direction<Tag>(tag1, PersonEnum.AGE.getType(), "24");

        Direction<Tag> d4 = new Direction<Tag>(tag2, PersonEnum.HANDSOME.getType(), "1");
        Direction<Tag> d5 = new Direction<Tag>(tag2, PersonEnum.HEIGHT.getType(), "180");
        Direction<Tag> d6 = new Direction<Tag>(tag2, PersonEnum.HEIGHT.getType(), "183");
        Direction<Tag> d7 = new Direction<Tag>(tag2, PersonEnum.HEIGHT.getType(), "185");
        Direction<Tag> d8 = new Direction<Tag>(tag2, PersonEnum.WEALTH.getType(), "100W");
        Direction<Tag> d9 = new Direction<Tag>(tag2, PersonEnum.WEALTH.getType(), "200W");

        Direction<Tag> d10 = new Direction<Tag>(tag3, PersonEnum.HEIGHT.getType(), "183");
        Direction<Tag> d11 = new Direction<Tag>(tag3, PersonEnum.HEIGHT.getType(), "184");
        Direction<Tag> d12 = new Direction<Tag>(tag3, PersonEnum.HEIGHT.getType(), "185");
        Direction<Tag> d13 = new Direction<Tag>(tag3, PersonEnum.WEIGHT.getType(), "180");
        Direction<Tag> d14 = new Direction<Tag>(tag3, PersonEnum.WEIGHT.getType(), "190");

        Direction<Tag> d15 = new Direction<Tag>(tag4, PersonEnum.AGE.getType(), "50");
        Direction<Tag> d16 = new Direction<Tag>(tag4, PersonEnum.AGE.getType(), "55");
        Direction<Tag> d17 = new Direction<Tag>(tag4, PersonEnum.WEALTH.getType(), "100W");
        Direction<Tag> d18 = new Direction<Tag>(tag4, PersonEnum.WEALTH.getType(), "300W");

        List<Direction<Tag>> directionList = new ArrayList<Direction<Tag>>();
        directionList.add(d1);
        directionList.add(d2);
        directionList.add(d3);
        directionList.add(d4);
        directionList.add(d5);
        directionList.add(d6);
        directionList.add(d7);
        directionList.add(d8);
        directionList.add(d9);
        directionList.add(d10);
        directionList.add(d11);
        directionList.add(d12);
        directionList.add(d13);
        directionList.add(d14);
        directionList.add(d15);
        directionList.add(d16);
        directionList.add(d17);
        directionList.add(d18);

        Pair pair = new Pair(tagList, directionList);
        return pair;
    }
}
