package com.payno.transaction.faststart;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;

/**
 * @author payno
 * @date 2020/6/2 10:42
 * @description
 */
public interface IMementoManager {
    /**
     * restore
     * @author: payno
     * @time: 2020/6/2 10:44
     * @description: 回滚状态
     * @param menento
     * @return: void
     */

    void restore(@Nonnull Menento menento);

    /**
     * createMemento
     * @author: payno
     * @time: 2020/6/2 10:44
     * @description:    创建时并缓存状态
     * @param msg
     * @return: com.payno.transaction.faststart.Menento
     */
    Menento createMemento(String msg);

    /**
     * getMemento
     * @author: payno
     * @time: 2020/6/2 10:57
     * @description:
     * @param
     * @return: com.payno.transaction.faststart.Menento
     */
    @Nullable Menento getMemento();

    class Default implements IMementoManager{

        LinkedList<Menento> menentoLinkedList = new LinkedList<>();

        @Override
        public void restore(Menento menento) {
            menentoLinkedList.remove(menento);
        }

        @Override
        public Menento createMemento(String msg) {
            Menento menento = new Menento.Default(msg);
            menentoLinkedList.add(menento);
            return menento;
        }

        @Override
        public Menento getMemento() {
            if(menentoLinkedList.size()>0){
                return menentoLinkedList.getLast();
            }else{
                return null;
            }
        }
    }
}
