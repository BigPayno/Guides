package com.payno.transaction.faststart;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author payno
 * @date 2020/6/2 10:46
 * @description
 */
public interface IMementoTemplate {
    @Nullable Menento get();
    void store(@Nonnull Menento menento);
    @Nullable Menento restoreMemento();
    class Default implements IMementoTemplate{
        Menento menento = null;
        IMementoManager mementoManager;

        public Default(@Nonnull IMementoManager mementoManager){
            this.mementoManager = mementoManager;
        }

        @Nullable
        @Override
        public Menento get() {
            return mementoManager.getMemento();
        }

        @Override
        public void store(@Nonnull Menento menento) {
            this.menento = menento;
        }

        @Nullable
        @Override
        public Menento restoreMemento() {
            if(menento==null){
                return null;
            }else{
                mementoManager.restore(menento);
                return mementoManager.getMemento();
            }
        }
    }
}
