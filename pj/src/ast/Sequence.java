package ast;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collector;

/**
 * This class is used to build a somewhat flat representation of a typical
 * AST. The structure is kept as a linked list to facilitate the traversal
 * of nodes in any order.
 *
 * @author Ben
 */
public class Sequence<N extends Node> implements ParentNode<Sequence<N>>, List<N>, Iterable<N> {
  protected List<N> arrayList_ = new ArrayList<>();
  protected Node parentNode_;

  public Sequence() {
    parentNode_ = null;
  }

  public Sequence(Collection<N> c) {
    arrayList_.addAll(c);
  }

  @SafeVarargs
  public Sequence(N... n) {
    arrayList_.addAll(Arrays.asList(n));
  }

  @SafeVarargs
  public static <A extends Node> Sequence<A> sequenceList(A... nodes) {
    Sequence<A> sequence = new Sequence<>();
    Collections.addAll(sequence, nodes);
    return sequence;
  }

  public static <A extends Node> Sequence<A> sequence(Collection<A> nodes) {
    Sequence<A> sequence = new Sequence<>();
    sequence.addAll(nodes);
    return sequence;
  }

  public static <A extends Node> Collector<A, Sequence<A>, Sequence<A>> toSequence() {
    return Collector.of(Sequence::new, Sequence::add, (list, values) -> {
      list.addAll(values);
      return list;
    });
  }

  @Override // List<?>
  public int size() {
    return arrayList_.size();
  }

  @Override // List<?>
  public boolean isEmpty() {
    return arrayList_.isEmpty();
  }

  @Override // List<?>
  public boolean contains(Object o) {
    return arrayList_.contains(o);
  }

  @Override // List<?>
  public Object[] toArray() {
    return arrayList_.toArray();
  }

  @Override // List<?>
  public <T> T[] toArray(T[] a) {
    return arrayList_.toArray(a);
  }

  @Override // List<?>
  public boolean add(N node) {
    return arrayList_.add(node);
  }

  @Override // List<?>
  public void add(int index, N node) {
    arrayList_.add(index, node);
  }

  @Override // List<?>
  public N set(int index, N n) {
    if (index < 0 || index >= arrayList_.size()) {
      throw new IllegalArgumentException("Illegal index. The index should be between 0 and " + arrayList_.size());
    }
    if (n == arrayList_.get(index)) {
      return n;
    }
    setAsParentNodeOf(n);
    return arrayList_.set(index, n);
  }

  @Override // List<?>
  public N get(int idx) {
    Objects.checkIndex(idx, arrayList_.size());
    return arrayList_.get(idx);
  }

  @Override // List<?>
  public N remove(int idx) {
    Objects.checkIndex(idx, arrayList_.size());
    N remove = arrayList_.remove(idx);
    if (remove != null) {
      remove.setParentNode(null);
    }
    return remove;
  }

  @Override // List<?>
  public boolean remove(Object object) {
    if (object instanceof Node) {
      return remove((Node) object);
    }
    return false;
  }

  @Override // List<?>
  public int indexOf(Object o) {
    return arrayList_.indexOf(o);
  }

  @Override // List<?>
  public int lastIndexOf(Object o) {
    return arrayList_.lastIndexOf(o);
  }

  @Override // List<?>
  public void clear() {
    while (!isEmpty()) {
      remove(0);
    }
  }

  @Override // List<?>
  public ListIterator<N> listIterator() {
    return new SequenceIterator(arrayList_);
  }

  @Override // List<?>
  public ListIterator<N> listIterator(int index) {
    return new SequenceIterator(arrayList_, index);
  }

  @Override // List<?>
  public List<N> subList(int fromIndex, int toIndex) {
    return arrayList_.subList(fromIndex, toIndex);
  }

  @Override // List<?>
  public boolean containsAll(Collection<?> c) {
    return new HashSet<>(arrayList_).containsAll(c);
  }

  @Override // List<?>
  public boolean addAll(Collection<? extends N> coll) {
    for (N node : coll) {
      add(node);
    }
    return !coll.isEmpty();
  }

  @Override // List<?>
  public boolean addAll(int index, Collection<? extends N> coll) {
    for (N node : coll) {
      add(index++, node);
    }
    return !coll.isEmpty();
  }

  @Override // List<?>
  public boolean removeAll(Collection<?> coll) {
    boolean changed = false;
    for (Object o : coll) {
      changed = remove(o) || changed;
    }
    return changed;
  }

  @Override // List<?>
  public boolean retainAll(Collection<?> coll) {
    boolean changed = false;
    for (Object o : this.stream().filter(it -> !coll.contains(it)).toArray()) {
      if (!coll.contains(o)) {
        changed = remove(o) || changed;
      }
    }
    return changed;
  }

  @Override
  public void forEach(Consumer<? super N> action) {
    arrayList_.forEach(action);
  }

  public boolean remove(Node node) {
    int index = arrayList_.indexOf(node);
    if (index != -1) {
      node.setParentNode(null);
    }
    return arrayList_.remove(node);
  }

  public Sequence<N> addFirst(N node) {
    add(0, node);
    return this;
  }

  public Sequence<N> addLast(N node) {
    add(node);
    return this;
  }

  public N removeFirst() {
    return remove(0);
  }

  public N removeLast() {
    return remove(arrayList_.size() - 1);
  }

  public Optional<N> getFirst() {
    if (!isEmpty()) {
      return Optional.of(get(0));
    }
    return Optional.empty();

  }

  public Optional<N> getLast() {
    if (!isEmpty()) {
      return Optional.of(get(size() - 1));
    }
    return Optional.empty();
  }

  public void trimToSize() {
    ArrayList<N> children = (ArrayList<N>) this.arrayList_;
    children.trimToSize();
  }

  public void ifNonEmpty(Consumer<? super Sequence<N>> consumer) {
    if (!isEmpty()) {
      consumer.accept(this);
    }
  }

  private void setAsParentNodeOf(List<? extends Node> children) {
    if (children != null) {
      for (ParentNode<?> node : children) {
        node.setParentNode(getParentNodeForChildren());
      }
    }
  }

  private void setAsParentNodeOf(Node node) {
    if (node != null) {
      node.setParentNode(getParentNodeForChildren());
    }
  }

  @Override
  public Optional<Node> getParentNode() {
    return Optional.ofNullable(parentNode_);
  }

  @Override
  public Sequence<N> setParentNode(Node other) {
    parentNode_ = other;
    setAsParentNodeOf(arrayList_);
    return this;
  }

  @Override
  public Node getParentNodeForChildren() {
    return parentNode_;
  }

  @Override
  public Iterator<N> iterator() {
    return new SequenceIterator(arrayList_);
  }

  @Override
  public int hashCode() {
    return arrayList_.hashCode();
  }

  @Override
  public boolean equals(Object object) {
    return arrayList_.equals(object);
  }

  protected class SequenceIterator implements ListIterator<N> {

    private final ListIterator<N> iterator;
    private N current_ = null;

    public SequenceIterator(List<N> list) {
      iterator = list.listIterator();
    }

    public SequenceIterator(List<N> list, int idx) {
      iterator = list.listIterator(idx);
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public N next() {
      current_ = iterator.next();
      return current_;
    }

    @Override
    public boolean hasPrevious() {
      return iterator.hasPrevious();
    }

    @Override
    public N previous() {
      current_ = iterator.previous();
      return current_;
    }

    @Override
    public int nextIndex() {
      return iterator.nextIndex();
    }

    @Override
    public int previousIndex() {
      return iterator.previousIndex();
    }

    @Override
    public void remove() {
      iterator.remove();
    }

    @Override
    public void set(N e) {
      final int index = arrayList_.indexOf(current_);
      if (index < 0 || index >= arrayList_.size()) {
        throw new IllegalArgumentException("Illegal index. The index should be between 0 and " + (arrayList_.size() - 1));
      }
      if (e != arrayList_.get(index)) {
        iterator.set(e);
      }
    }

    @Override
    public void add(N e) {
      iterator.add(e);
    }
  }
}

