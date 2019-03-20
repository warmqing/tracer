package com.vanchin.gunny.tracer.index;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangqing21
 * @date 2019/3/12 19:55
 */
public class Index {

    private final String NO_LIMIT = "other";

    private Map<String, Map<String, List<Target>>> directionMap = new HashMap<String, Map<String, List<Target>>>();
    private Map<String, Map<String, BitSet>> bitSetMap = new HashMap<String, Map<String, BitSet>>();
    private Map<Integer, Target> targetMap = new HashMap<Integer, Target>();
    private Map<String, Integer> targetIndexMap = new HashMap<String, Integer>();

    public Index(List<Direction<Target>> directionList, List<Target> targets) {
        this.init(directionList, targets);
    }

    private void defaultInit(List<Target> targets, List<String> directions) {
        if (targets == null || directions == null) {
            return;
        }

        for(String dName : directions){
            Map<String, List<Target>> directionValueMap = directionMap.get(dName);
            if (directionValueMap == null) {
                directionValueMap = new HashMap<String, List<Target>>();
                directionMap.put(dName, directionValueMap);
            }

            directionValueMap.put(NO_LIMIT, targets);
        }
    }

    private void addDirection(Direction<Target> direction) {
        if (direction == null) {
            return;
        }
        String dName = direction.getName();
        String dValue = direction.getValue();
        Target target = direction.getTarget();

        Map<String, List<Target>> directionValueMap = directionMap.get(dName);
        if (directionValueMap == null) {
            directionValueMap = new HashMap<String, List<Target>>();
            directionMap.put(dName, directionValueMap);
        }
        List<Target> targetList = directionValueMap.get(dValue);
        if (targetList == null) {
            targetList = new ArrayList<Target>();
            directionValueMap.put(dValue, targetList);
        }
        targetList.add(target);
    }

    private void init(List<Direction<Target>> directionList, List<Target> targets) {

        for (Direction<Target> d : directionList) {
            this.addDirection(d);
        }

        int size = targets.size();

        for (int i = 0; i < size; i++) {
            targetIndexMap.put(targets.get(i).getUniqueId(), i);
            targetMap.put(i, targets.get(i));
        }

        for (Map.Entry<String, Map<String, List<Target>>> entry : directionMap.entrySet()) {

            String dName = entry.getKey();
            Map<String, List<Target>> values = entry.getValue();

            Map<String, BitSet> rs = bitSetMap.get(dName);
            if (rs == null) {
                rs = new HashMap<String, BitSet>();
                bitSetMap.put(dName, rs);
            }

            for (Map.Entry<String, List<Target>> dv : values.entrySet()) {
                String dValue = dv.getKey(); // 定向维度
                List<Target> targetValues = dv.getValue(); // 投放单ID

                BitSet b = rs.get(dValue);
                if (b == null) {
                    b = new BitSet(size);
                    rs.put(dValue, b);
                }
                for (Target target : targetValues) {
                    b.set(targetIndexMap.get(target.getUniqueId()));
                }
            }
        }
    }

    public Map<String, Map<String, BitSet>> getBitSetMap() {
        return bitSetMap;
    }

    public Map<Integer, Target> getTargetMap() {
        return targetMap;
    }

    public Map<String, Integer> getTargetIndexMap() {
        return targetIndexMap;
    }
}
