/*
 *  UnBBayes
 *  Copyright (C) 2002, 2008 Universidade de Brasilia - http://www.unb.br
 *
 *  This file is part of UnBBayes.
 *
 *  UnBBayes is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  UnBBayes is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with UnBBayes.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package unbbayes.prs.bn;

import java.io.File;

import junit.framework.TestCase;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;

public class TestProbabilisticNetwork extends TestCase {

    public static File ASIA_FILE = new File("src/test/resources/testCases/asia.net");
    public static double DELTA = 0.0001;

    private ProbabilisticNetwork net;

    public TestProbabilisticNetwork(String s) {
        super(s);
    }

    public void setUp() throws Exception {
        BaseIO io = new NetIO();
        net = (ProbabilisticNetwork)io.load(ASIA_FILE);
    }

    public void testCompileAsia() throws Exception {
        net.compile();
        ProbabilisticNode temp = (ProbabilisticNode)net.getNode("D");
        assertEquals(temp.getMarginalAt(0), 0.4360, DELTA);

        temp = (ProbabilisticNode)net.getNode("B");
        assertEquals(temp.getMarginalAt(0), 0.45, DELTA);

        temp = (ProbabilisticNode)net.getNode("L");
        assertEquals(temp.getMarginalAt(0), 0.055, DELTA);

        temp = (ProbabilisticNode)net.getNode("L");
        assertEquals(temp.getMarginalAt(0), 0.055, DELTA);

        temp = (ProbabilisticNode)net.getNode("T");
        assertEquals(temp.getMarginalAt(0), 0.0104, DELTA);

        temp = (ProbabilisticNode)net.getNode("X");
        assertEquals(temp.getMarginalAt(0), 0.1103, DELTA);

        temp = (ProbabilisticNode)net.getNode("S");
        assertEquals(temp.getMarginalAt(0), 0.50, DELTA);

        temp = (ProbabilisticNode)net.getNode("E");
        assertEquals(temp.getMarginalAt(0), 0.0648, DELTA);

        temp = (ProbabilisticNode)net.getNode("A");
        assertEquals(temp.getMarginalAt(0), 0.01, DELTA);
    }
}
