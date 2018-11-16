package cn.com.cslc.aw.test;

import java.util.HashSet;
import java.util.Set;

public class TestSet {

	public static void main(String[] args) {
		Set<Long> longSet = new HashSet<Long>();
		longSet.add(Long.parseLong("1"));
		longSet.add(Long.parseLong("1"));
		System.out.println(longSet.size());
	}

}
