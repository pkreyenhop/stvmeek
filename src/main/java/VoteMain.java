// Voting Systems Toolbox
// Copyright (C) 2001,2002 Roy Ward
// $Header: /cvsroot/votesystem/votesystem/src/VoteMain.java,v 1.7 2002/07/09 10:09:42 royward0 Exp $
//
// Voting Systems Toolbox is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License as published
// by the Free Software Foundation; version 2 of the License.
//
// Voting Systems Toolbox is distributed in the hope that it will be useful,
// but WITHOUT ANY/ WARRANTY; without even the implied warranty of MERCHANTABILITY
// or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
// for more details.
//
// You should have received a copy of the GNU General Public License along with
// Voting Systems Toolbox; if not, write to the Free Software Foundation, Inc.,
// 59 Temple Place, Suite 330, Boston, MA  02111-1307.

import vote.Parameters;
import vote.STV;
import vote.Status;
import vote.Table;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Hashtable;

class VoteMain {

    private static Table votes = null;

    public static void main(String[] args) {
        Status sys = null;
        try {
            String[] extras = {"input", "output"};
            Hashtable singles = new Hashtable();
            singles.put("log", singles);
            singles.put("debug", singles);
            singles.put("nostrict-legal", singles);
            singles.put("nostrict-min-ranks", singles);
            Parameters params = new Parameters();
            params.insert(args, extras, singles);
            String filename = params.extract("input");
            sys = new Status(params);
            try {
                LineNumberReader lines = new LineNumberReader(new FileReader(filename));
                votes = new Table(lines, sys);
            } catch (IOException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
            STV s = new STV(votes, sys);
            int seats = Integer.valueOf(params.extract("seats"));
            params.checkEmpty();
            s.vote(seats);
            s.print();

            sys.printlnOut("Done!");
        } catch (Exception e) {
            System.err.println("Error:" + e.getMessage());
            System.err.println("Program aborted");
            if (sys.debug()) e.printStackTrace();
            sys.printlnLog("Error:" + e.getMessage());
            sys.printlnLog("Program aborted");
        }
    }

}
