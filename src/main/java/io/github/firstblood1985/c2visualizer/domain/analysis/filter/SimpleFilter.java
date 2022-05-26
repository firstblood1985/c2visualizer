package io.github.firstblood1985.c2visualizer.domain.analysis.filter;

/**
 * usage of this class: SimpleStringFilter
 * created by limin @ 2022/5/15
 */
public abstract class SimpleFilter extends AnalysisFilter {
    protected SimpleFilter(FilterTarget filterTarget, String filterRaw) {
        super(filterTarget, filterRaw);
    }

    protected Boolean filter(String toCompare) {
        if (null == toCompare)
            return false;

        if (operator == FilterOperator.BETWEEN)
            return toCompare.compareTo(params.get(0)) >= 0 && toCompare.compareTo(params.get(1)) <= 0;

        if (operator == FilterOperator.GET)
            return toCompare.compareTo(params.get(0)) >= 0;


        if (operator == FilterOperator.LET)
            return toCompare.compareTo(params.get(0)) <= 0;

        return false;
    }

    protected Boolean filter(Integer toCompare) {
        if (null == toCompare) return false;

        if (operator == FilterOperator.BETWEEN)
            return toCompare >= Integer.valueOf(params.get(0)) && toCompare <= Integer.valueOf(params.get(1));

        if (operator == FilterOperator.GET)
            return toCompare >= Integer.valueOf(params.get(0));

        if (operator == FilterOperator.LET)
            return toCompare <= Integer.valueOf(params.get(0));

        return false;
    }
}
