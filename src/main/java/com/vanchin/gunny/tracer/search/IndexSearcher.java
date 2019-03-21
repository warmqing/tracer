package com.vanchin.gunny.tracer.search;

import com.vanchin.gunny.tracer.index.Direction;
import com.vanchin.gunny.tracer.index.Index;
import com.vanchin.gunny.tracer.index.Target;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

/**
 * @author vanchin
 * @date 2019/3/19 17:47
 */
public class IndexSearcher {
    private Index index;

    public IndexSearcher(Index index) {
        this.index = index;
    }

    public List<Target> search(List<Direction<Target>> directions) {
        List<BitSet> bitSetList = new ArrayList<BitSet>();
        for (Direction direction : directions) {
            String dType = direction.getType();
            String dValue = direction.getValue();

            Map<String, BitSet> valueMap = index.getBitSetMap().get(dType);
            if (valueMap == null) {
                continue;
            }

            BitSet nlbs = valueMap.get(Index.NO_LIMIT);
            BitSet bs = valueMap.get(dValue);

            BitSet nlbs_clone = (BitSet) nlbs.clone();

            if (bs != null) {
                nlbs_clone.or(bs);
            }

            bitSetList.add(nlbs_clone);
        }
        if (bitSetList.isEmpty()) {
            return null;
        }
        BitSet res = bitSetList.get(0);
        for (BitSet bs : bitSetList) {
            res.and(bs);
        }

        List<Target> targets = new ArrayList<Target>();
        for (int i = res.nextSetBit(0); i >= 0; i = res.nextSetBit(i + 1)) {
            if (i == Integer.MAX_VALUE) {
                break;
            }

            targets.add(index.getTargetMap().get(i));

        }
        return targets;
    }
}
