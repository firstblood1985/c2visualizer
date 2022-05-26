package io.github.firstblood1985.c2visualizer.domain.analysis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * usage of this class: PaceAnalysisRequestTest
 * created by limin @ 2022/5/15
 */
@RunWith(JUnit4.class)
public class PaceAnalysisRequestTest {

    PaceAnalysisRequestRaw raw;
    @Before
    public void setUp(){
        raw = new PaceAnalysisRequestRaw();
        raw.setStartDate("2022-05-01");
        raw.setEndDate("2022-05-10");
        raw.setPaceFilterRaw("BETWEEN 1:53 1:52");
        raw.setDistanceFilterRaw("GET 10000");
    }

    @Test
    public void testBuildPaceAnalysisRequest(){
        PaceAnalysisRequest paceAnalysisRequest = new PaceAnalysisRequest(raw);

        Assert.assertEquals("2022-05-10",paceAnalysisRequest.getEndDate().toString());
        Assert.assertEquals(2,paceAnalysisRequest.getAnalysisFilters().size());
    }
}
