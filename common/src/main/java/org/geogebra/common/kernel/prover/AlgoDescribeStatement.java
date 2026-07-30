package org.geogebra.common.kernel.prover;

import static org.geogebra.common.cas.giac.CASgiac.CustomFunctions.setDependencies;

import org.geogebra.common.kernel.Construction;
import org.geogebra.common.kernel.algos.AlgoElement;
import org.geogebra.common.kernel.commands.Commands;
import org.geogebra.common.kernel.geos.GeoBoolean;import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoText;
import org.geogebra.common.util.Prover;
import org.geogebra.common.util.debug.Log;

/**
 * Describe the statement by English (or localized) descriptions.
 *
 * @author Zoltan Kovacs <zoltan@geogebra.org>
 */

public class AlgoDescribeStatement extends AlgoElement {

    private GeoElement root; // input
    private GeoText text; // output
    private boolean breakLines; // whether break lines in the output

    public AlgoDescribeStatement(Construction cons, String label, GeoElement root, boolean breakLines) {
        super(cons);
        this.root = root;
        this.breakLines = breakLines;

        text = new GeoText(cons);
        setInputOutput(); // for AlgoElement
        compute();
        text.setLabel(label);
    }

    @Override
    public Commands getClassName() {
        return Commands.DescribeStatement;
    }

    // for AlgoElement
    @Override
    protected void setInputOutput() {
        input = new GeoElement[1];
        input[0] = root;

        super.setOutputLength(1);
        super.setOutput(0, text);
        setDependencies(); // done by AlgoElement
    }

    /**
     * Returns the output for the DescribeStatement command
     * @return A textual description of the statement
     */
    public GeoText getGeoText() {
        return text;
    }

    public void compute() {
        getGeoText().setTextString(Prover.getTextFormat(root, true, breakLines ? "\n" : " "));

    }
}