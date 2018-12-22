package reference_1;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * ç±»åˆ«/åº�å�·é”®å€¼å¯¹é›†å�ˆã€‚ç”¨äºŽæŠŠè½¬ç§»çŸ©é˜µçš„è¡Œåˆ—å�·æˆ–è€…å�‘å°„çŸ©é˜µä¸­çš„è¡Œåº�å�·å¯¹åº”ä¸Šç±»åˆ«ã€‚
 * @author yuncong
 *
 */
public class ClassDictionary {
	private Map<String, Integer> dictionary;
	
	private ClassDictionary() {
		dictionary = new HashMap<String, Integer>();
		// è¯�é¦–
		dictionary.put(HiddenStates.BEGIN.getAlias(), 0);
		// è¯�ä¸­
		dictionary.put(HiddenStates.MIDDLE.getAlias(), 1);
		// è¯�å°¾
		dictionary.put(HiddenStates.END.getAlias(), 2);
		// å�•å­—æˆ�è¯�
		dictionary.put(HiddenStates.SINGLE.getAlias(), 3);
	}
	
	private static class SingletonHolder {
		private static ClassDictionary classDictionary = new ClassDictionary();
	}
	
	public static ClassDictionary getInstance() {
		return SingletonHolder.classDictionary;
	}
	
	public int size() {
		return dictionary.size();
	}
	
	public Set<String> classs() {
		return dictionary.keySet();
	}
	

	public Integer value(String key) {
		return dictionary.get(key);
	}
	
	public String key(Integer value) {
		for (Entry<String, Integer> entry : dictionary.entrySet()) {
			if (entry.getValue() == value) {
				return entry.getKey();
			}
		}
		return null;
	}
}
