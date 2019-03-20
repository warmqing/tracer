package com.vanchin.gunny.tracer.search;

import com.vanchin.gunny.tracer.index.Direction;
import com.vanchin.gunny.tracer.index.Index;
import com.vanchin.gunny.tracer.index.Target;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

/**
 * @author wangqing21
 * @date 2019/3/19 17:47
 */
public class IndexSearcher {
    private Index index;

    public IndexSearcher(Index index){
        this.index = index;
    }

    public List<Target> search(List<Direction<Target>> directions) {
        List<BitSet> bitSetList = new ArrayList<BitSet>();
        for (Direction direction : directions) {
            String dName = direction.getName();
            String dValue = direction.getValue();

            Map<String, BitSet> map = index.getBitSetMap().get(dName);
            if (map == null) {
                continue;
            }
            BitSet b = map.get(dValue);
            if (b == null) {
                return null;
            } else {
                bitSetList.add(b);
            }
        }
        if (bitSetList.isEmpty()) {
            return null;
        }
        BitSet res_0 = bitSetList.get(0);
        BitSet res = (BitSet) res_0.clone();
        for (BitSet b : bitSetList) {
            res.and(b);
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
