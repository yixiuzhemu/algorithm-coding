package com.ly.algorithm.coding;

import com.ly.algorithm.Node;
import com.ly.algorithm.RandomNode;

/**
 * @author Ly
 * @create 2021/5/14 15:31
 * @desc
 **/
public class NodeCoding {

    /**
     * 链表长度为奇数时，返回中点 ，为偶数时返回上中点
     * @param head
     * @return
     */
    public static Node midOrUpMidNode(Node head){
        if( head == null || head.getNext() ==  null || head.getNext().getNext() == null){
            return head;
        }

        Node slow = head.getNext();
        Node fast = head.getNext().getNext();
        while(slow.getNext() != null && fast.getNext().getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    /**
     * 链表长度为奇数时，返回中点 ，为偶数时返回下中点
     * @param head
     * @return
     */
    public static Node midOrDownMidNode(Node head){
        if(head == null || head.getNext() == null){
            return head;
        }
        Node slow = head.getNext();
        Node fast = head.getNext();
        while (fast.getNext() != null && fast.getNext().getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    /**
     * 链表长度为奇数时，返回中点 ，为偶数时返回上中点的前一个
     * @param head
     * @return
     */
    public static Node midOrPreUpMidNode(Node head){
        if( head == null || head.getNext() ==  null || head.getNext().getNext() == null){
            return head;
        }
        Node slow = head;
        Node fast = head.getNext().getNext();
        while(fast.getNext() != null && fast.getNext().getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    /**
     * 链表长度为奇数时，返回中点 ，为偶数时返回下中点的后一个
     * @param head
     * @return
     */
    public static Node midOrgetNextDownMidNode(Node head){
        if( head == null || head.getNext() ==  null ){
            return null;
        }
        if(head.getNext().getNext() == null){
           return head;
        }
        Node slow = head;
        Node fast = head.getNext();
        while(fast.getNext() != null && fast.getNext().getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    /**
     * 链表实现荷兰国旗
     * @param head
     * @param partitionValue
     * @return
     */
    public static Node dutchFlag(Node head,int partitionValue){
        Node lessH = null;
        Node lessE = null;
        Node equalH = null;
        Node equalE = null;
        Node moreH = null;
        Node moreE = null;
        Node next = null;
        while(head != null){
            next = head.getNext();
            head.setNext(null);
            int headValue = head.getValue();
            if(partitionValue > headValue){
                if(lessH == null){
                    lessH = head;
                    lessE = head;
                }else{
                    lessE.setNext(head);
                    lessE = head;
                }
            }else if(partitionValue == headValue){
                if(equalH == null){
                    equalH = head;
                    equalE = head;
                }else{
                    equalE.setNext(head);
                    equalE = head;
                }
            }else if(partitionValue < headValue){
                if(moreH == null){
                    moreH = head;
                    moreE = head;
                }else{
                    moreE.setNext(head);
                    moreE = head;
                }
            }
            head = next;
        }
        Node newHead = null;
        if(lessH != null){
            newHead = lessH;
        }
        if(equalH != null){
            if(newHead == null){
                newHead = equalH;
            }else{
                lessE.setNext(equalH);
            }
        }
        if(moreH != null){
            if(newHead == null){
                newHead = moreH;
            }else if(equalE != null){
                equalE.setNext(moreH);
            }else if(lessE != null){
                lessE.setNext(moreH);
            }
        }
        return newHead;
    }

    /**
     * 给定一个由RandomNode组成的无环单链表的头节点head，完成链表的复制
     * 时间复杂度O(n) 额外空间复杂度O（1）
     * @param head
     * @return
     */
    public static RandomNode copyRandomNode(RandomNode head){
        RandomNode cur = head;
        while(cur != null){
            RandomNode next = cur.getNext();
            RandomNode copyNode = cur.copyNode();
            cur.setNext(copyNode);
            copyNode.setNext(next);
            cur =  next;
        }
        cur = head;
        while(cur != null){
            RandomNode next = cur.getNext().getNext();
            RandomNode copyNode = cur.getNext();
            copyNode.rand = cur.rand == null ? null : cur.rand.getNext();
            cur = next;
        }
        cur = head;
        RandomNode copyHead = cur.getNext();
        while(cur != null){
            RandomNode next = cur.getNext().getNext();
            RandomNode copyNode = cur.getNext();
            copyNode.setNext(next != null ? next.getNext() : null);
            cur.setNext(next);
            cur = next;
        }
        return copyHead;
    }

    /**
     * 给定两个可能有环也可能无环的单链表，如果两链表相交，返回第一个相交的节点，如果不相交返回null
     * 1.两个链表都无环，可能会相交
     * 2.一个链表无环，一个链表有环，一定不可能相交，因为getNext()指针只要一个
     * 3.两个链表都有环，可能相交，一定共用环
     * @param head
     */
    /**
     * 两个链表都是无环结构
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1,Node head2){
        Node cur1 = head1;
        Node cur2 = head2;
        //两个链表长度的差值
        int n = 0;
        while(cur1 != null){
            n++;
            cur1 = cur1.getNext();
        }
        while(cur2 != null){
            n--;
            cur2 = cur2.getNext();
        }
        //将长链表赋值给cur1，短链表赋值给cur2
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1? head2:head1;
        n = Math.abs(n);
        //长链表先走 n 步，然后两链表再一起走，最终一定会在相交点相遇
        while(n > 0){
            n--;
            cur1 = cur1.getNext();
        }
        while(cur1 != cur2){
            cur1 = cur1.getNext();
            cur2 = cur2.getNext();
        }
        return cur1;

    }

    /**
     * 两个链表都有环的情况，一定共用环
     * 1.两个环不相交
     * 2.两个环只有一个相交点
     * 3.两个环有两个相交点
     * @param head1
     * @param head2
     * @return
     */
    public static Node haveLoop(Node head1,Node head2){
        Node cur1 = head1;
        Node cur2 = head2;
        //非环部分的差值
        int n = 0;
        Node loopNode1 = NodeCoding.getLoopNode(head1);
        Node loopNode2 = NodeCoding.getLoopNode(head2);
        if(loopNode1 == null || loopNode2 == null){
            return null;
        }
        //相交只有一个点
        if(loopNode1 == loopNode2){
            while(cur1 != loopNode1){
                n++;
                cur1 = cur1.getNext();
            }
            while(cur2 != loopNode2){
                n--;
                cur2 = cur2.getNext();
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1? head2:head1;


            n = Math.abs(n);
            while(n > 0){
                n--;
                cur1 = cur1.getNext();
            }
            while(cur1 != cur2){
                cur1 = cur1.getNext();
                cur2 = cur2.getNext();
            }
            return cur1;
        }else{//相交有两个点
            cur1 = loopNode1.getNext();
            while(cur1 != loopNode1){
                if(cur1 == loopNode2){
                    return cur1;
                }
                cur1 = cur1.getNext();

            }
            return null;
        }

    }

    /**
     * 返回有环链表的节点
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head){
        if(head == null || head.getNext() == null || head.getNext().getNext() == null){
            return null;
        }
        Node slow = head.getNext();
        Node fast = head.getNext().getNext();
        while(!slow.equals(fast)){
            if(fast.getNext() == null || fast.getNext().getNext() == null){
                return null;
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        fast = head;
        while(!slow.equals(fast)){
            fast = fast.getNext();
            slow = slow.getNext();
        }
        return slow;
    }



    public static void main(String[] args) {


    }
}

