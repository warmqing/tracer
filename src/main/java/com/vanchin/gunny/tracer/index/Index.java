package com.vanchin.gunny.tracer.index;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vanchin
 * @date 2019/3/12 19:55
 */
public class Index {

    public static final String NO_LIMIT = "no_limit";

    private Map<String, Map<String, BitSet>> bitSetMap = new HashMap<String, Map<String, BitSet>>();
    private Map<Integer, Target> targetMap = new HashMap<Integer, Target>();
    private Map<String, Integer> targetIndexMap = new HashMap<String, Integer>();
    private int targetSize;

    public Index(List<String> types, List<Target> targets, List<Direction<Target>> directionList) {
        this.init(types, targets, directionList);
    }

    private void defaultInit(List<Target> targets, List<String> types) {
        if (targets == null || types == null) {
            return;
        }

        targetSize = targets.size();

        for (int i = 0; i < targetSize; i++) {
            targetIndexMap.put(targets.get(i).getUniqueId(), i);
            targetMap.put(i, targets.get(i));
        }

        for (String dType : types) {

            Map<String, BitSet> valueMap = bitSetMap.get(NO_LIMIT);
            if (valueMap == null) {
                valueMap = new HashMap<String, BitSet>();
                bitSetMap.put(dType, valueMap);
            }

            BitSet bs = new BitSet(targetSize);
            for (Target target : targets) {
                bs.set(targetIndexMap.get(target.getUniqueId()));
            }

            valueMap.put(NO_LIMIT, bs);
        }
    }

    private void addDirection(Direction<Target> direction) {
        if (direction == null) {
            return;
        }
        String dType = direction.getType();
        String dValue = direction.getValue();
        Target target = direction.getTarget();

        Map<String, BitSet> valueMap = bitSetMap.get(dType);
        if (valueMap == null) {
            valueMap = new HashMap<String, BitSet>();
            bitSetMap.put(dType, valueMap);
        }

        BitSet bs = valueMap.get(dValue);
        if (bs == null) {
            bs = new BitSet(targetSize);
            valueMap.put(dValue, bs);
        }

        bs.set(targetIndexMap.get(target.getUniqueId()));

        BitSet nlBs = valueMap.get(NO_LIMIT);
        if (nlBs == null) {
            nlBs = new BitSet(targetSize);
            valueMap.put(NO_LIMIT, nlBs);
        }

        nlBs.clear(targetIndexMap.get(target.getUniqueId()));
    }

    private void init(List<String> types, List<Target> targets, List<Direction<Target>> directionList) {

        defaultInit(targets, types);

        for (Direction<Target> d : directionList) {
            this.addDirection(d);
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
