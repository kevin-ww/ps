package com.philips.cn.hr.pps.workbook.sheet;


import com.philips.cn.hr.pps.common.Column;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kevin on 2015/1/7.
 */
public class SAPInput {

//    Column[] columns;

//    static final Column[] columns;


    private static Set<Column> columns;

    static {

//       Amount +/-	"Posting Key
//       (SHKZG or NEWBS)"	"Upload amount
//       (WRBTR)"	"General Ledger Account
//               (HKONT or NEWKO)"	"Value Date
//       (VALUT)"	"Item Text
//       (SGTXT)"	"Cost Center
//       (KOSTL)"

//       Column column1 = new Column();
//       column1.setComment("Amount +/-");

        columns = new HashSet<Column>();

        columns.add(new Column(13, "Amount+"));

        columns.add(new Column(15, "Upload amount+"));

    }


    public SAPInput(Set<Column> columns) {
        this.columns = columns;
    }
}
