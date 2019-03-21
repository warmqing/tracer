package com.vanchin.gunny.tracer.search;

import com.vanchin.gunny.tracer.index.Index;

/**
 * @author vanchin
 * @date 2019/3/19 19:39
 */
public class SearcherFactory {
    public static IndexSearcher newSearcher(Index index) {
        return new IndexSearcher(index);
    }
}
