package advisor.imports.importselector;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;

/**
 * @author payno
 * @date 2020/6/3 11:26
 * @description
 */
public class ActiveModeSelector extends AdviceModeImportSelector<EnableImport> {
    @Override
    protected String[] selectImports(AdviceMode adviceMode) {
        /**
         *  注入String的类
         */
        return new String[]{"advisor.imports.importselector.ImportAwareRunner"};
    }
}
