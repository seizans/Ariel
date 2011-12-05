package com.gmail.seizans.Tree;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

/*
以下をテストする
BinaryTree#()
 - 要素が一つinsertされていること
BinaryTree#search(K key)
 - 普通の木を作ってsearch：最初、途中、最後、ない要素
 - 同一keyが複数ある場合、最初に見つけたものになっていること
 - 要素が見つからなかった場合はnullを返すこと
 - 空の木ならnullを返すこと
BinaryTree#insert(K key, V value)
 - 穴あきツリーへのinsert
BinaryTree#remove(K key)
 - 空ツリーへのremoveがNoSuchElementException()を返すこと
 - 該当する要素がない場合はNoSuchElementException()を返すこと
 - 指定したkeyが消えること：正常系
 - 順番に全部のkeyに対してremoveした後に空ツリーになること：正常系
 - 同じkeyが複数あった場合に、深さ優先でremoveすること：正常系
 - root要素を消した場合の挙動
 - 右側を消したときと左側を消したときの挙動
 - 消したkeyに対するremoveを再度呼んだ場合にNoSuchElementException()を返すこと
iterator()
 - depthFirstIterator() と同じ結果を返すこと
 - breadthFirstIterator() と違う結果を返すこと
iterator()#hasNext()
 - 要素数の回数まではtrue、その次はfalseを返すこと
iterator()#next()
 - nextし終わったときに全部の要素をなめていること
 - 要素数の回数までは動作し、その次はNoSuchElementExceptionを返すこと
iterator()#remove()
 - UnSupportedOperationExceptionを返すこと
breadthFirstIterator()#hasNext()
 - 要素数の回数まではtrue、その次はfalseを返すこと
breadthFirstIterator()#next()
 - nextし終わったときに全部の要素をなめていること
 - 要素数の回数までは動作し、その次はNoSuchElementExceptionを返すこと
breadthFirstIterator()#remove()
 - UnSupportedOperationExceptionを返すこと
 */
public class BinaryTreeTest {

	private final String VAL_PRE = "ValuePrefix_";

	@Test
	public void test1() {
		StringBuilder[] src = { new StringBuilder("1"), new StringBuilder("2")};
		List<StringBuilder> dest = Arrays.asList(src);
		dest.add(new StringBuilder("3"));
	}

	@Test
	public void constructorTest() {
		BinaryTree<Integer, String> tree = new BinaryTree<Integer, String>(0, VAL_PRE + 0);
		String value1 = tree.search(0);
		assertThat(value1, is(VAL_PRE + 0));
		String value2 = tree.search(1);
		assertThat(value2, is(nullValue()));
		tree.remove(0);
		String value3 = tree.search(0);
		assertThat(value3, is(nullValue()));
	}

	@Test
	public void searchInsertRomeveTest() {
		final int N = 50;
		BinaryTree<Integer, String> tree = new BinaryTree<Integer, String>(0, VAL_PRE + 0);
		for (int i = 1; i < N; i++) {
			int key = i;
			String value = VAL_PRE + i;
			tree.insert(key, value);
		}
		for (int i = 0; i < N; i++) {
			int key = i;
			String value = tree.search(key);
			assertThat(value, is(VAL_PRE + i));
		}
		String value1 = tree.search(-1);
		assertThat(value1, is(nullValue()));
		String value2 = tree.search(N + 1);
		assertThat(value2, is(nullValue()));

		try {
			tree.remove(-1);
			fail();
		} catch(NoSuchElementException e) {
		}

		try {
			tree.remove(51);
			fail();
		} catch(NoSuchElementException e) {
		}

		for (int i = 0; i < N; i++) {
			tree.remove(i);
		}
		
		for (int i = 0; i< N; i++) {
			String s = tree.search(i);
			assertThat(s, is(nullValue()));
		}

		//System.out.println(tree.toDString());
	}

	@Test
	public void iteratorTest() {
		final int N = 50;
		BinaryTree<Integer, String> tree = new BinaryTree<Integer, String>(0, VAL_PRE + 0);
		for (int i = 1; i < N; i++) {
			int key = i;
			String value = VAL_PRE + i;
			tree.insert(key, value);
		}

		Iterator<BinaryTree<Integer, String>> iter = tree.iterator();
		for (int i = 0; i < N; i++) {
			try {
				assertThat(iter.hasNext(), is(true));
				iter.next();
			} catch(NoSuchElementException e) {
				fail();
			}
		}
		assertThat(iter.hasNext(), is(false));
		try {
			iter.next();
			fail();
		} catch(NoSuchElementException e) {
		}

		// TODO verify below
		/*
		Iterator<BinaryTree<Integer, String>> depthIter = tree.depthFirstIterator();
		Iterator<BinaryTree<Integer, String>> breadthIter = tree.breadthFirstIterator();
		assertThat(iter, is(equalTo(depthIter)));
		assertThat(iter, is(not(equalTo(breadthIter))));
		*/
	}

	/*
	@Test
	public void insertTest() {
		BinaryTree<Integer, String> root = new BinaryTree<Integer, String>(10, "ten");
		root.insert(5, "five");
		root.insert(13, "thirteen");
		root.insert(2, "two");
		root.insert(6, "six");
		root.insert(12, "twelve");
		root.insert(20, "twenty");
		System.out.println(root);
		System.out.println(root.toDString());
		for (BinaryTree<Integer, String> node: root) {
			System.out.println(node.toSimpleString());
		}
		System.out.println(root.search(6));
		root.remove(6);
		System.out.println(root);
		root.remove(5);
		System.out.println(root);
	}
	*/

	/*
	@Test
	public void BreadthFirstIteratorTest() {
		BinaryTree<Integer, String> root = new BinaryTree<Integer, String>(10, "ten");
		root.insert(5, "five");
		root.insert(13, "thirteen");
		root.insert(2, "two");
		root.insert(6, "six");
		root.insert(12, "twelve");
		root.insert(20, "twenty");
		Iterator<BinaryTree<Integer, String>> iter = root.breadthFirstIterator();
		while (iter.hasNext()) {
			BinaryTree<Integer, String> tree = iter.next();
			System.out.println(tree);
		}
	}
	*/

}
