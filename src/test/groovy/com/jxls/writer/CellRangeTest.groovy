package com.jxls.writer

import spock.lang.Specification

/**
 * @author Leonid Vysochyn
 * Date: 1/26/12 2:56 PM
 */
class CellRangeTest extends Specification{
    def "test move cells with row block"(){
        given:
            CellRange cellRange = new CellRange(new Cell(0,0), 10, 10)
            int startRow = 3;
            int endRow = 5;
            int col = 5;
            int shift = 3;
        when:
            cellRange.shiftCellsWithRowBlock(startRow, endRow, col, shift)
        then:
            assert cellRange.getCell(3, 4) == new Cell(3, 4)
            assert cellRange.getCell(6, 3) == new Cell(9, 3)
            assert cellRange.getCell(6, 5) == new Cell(9, 5)
            assert cellRange.getCell(6, 6) == new Cell(6, 6)
    }

    def "test move cells with column block"(){
        given:
            CellRange cellRange = new CellRange(new Cell(0,0), 10, 10)
            int startCol = 3;
            int endCol = 5;
            int row = 5;
            int shift = -3;
        when:
            cellRange.shiftCellsWithColBlock(startCol, endCol, row, shift)
        then:
            assert cellRange.getCell(4, 3) == new Cell(4, 3)
            assert cellRange.getCell(3, 6) == new Cell(3, 3)
            assert cellRange.getCell(6, 7) == new Cell(6, 7)
            assert cellRange.getCell(5, 7) == new Cell(5, 4)
    }
    
    def "test exclude cells"(){
        given:
            CellRange cellRange = new CellRange(new Cell(0,0), 10, 10)
        when:
            cellRange.excludeCells(3, 4, 5, 7)
        then:
            assert cellRange.isExcluded(3,5)
            assert cellRange.isExcluded(3,6)
            assert cellRange.isExcluded(3,7)
            assert cellRange.isExcluded(4,5)
            assert cellRange.isExcluded(4, 6)
            assert !cellRange.isExcluded(3, 3)
            assert !cellRange.isExcluded(2, 5)
            assert !cellRange.isExcluded(4, 8)
    }
}
