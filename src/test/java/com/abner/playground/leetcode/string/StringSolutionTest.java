package com.abner.playground.leetcode.string;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import java.util.List;

class StringSolutionTest {

    @Test
    void findAnagrams() {
        StringSolution solution = new StringSolution();
        List<Integer> res = solution.findAnagrams("cbaebabacd","abc");
        Assert.assertEquals(2, res.size());
    }
}