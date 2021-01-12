package ir.husseinrasti

object Algorithm {

    private var countObjects: Int = 0
    private var countBox: Int = 0
    private var boxSize: Int = 0

    private val listObjects = arrayListOf<Int>()
    private val listBox = arrayListOf<Int>()

    fun run() {
        inputValue("Enter count of objects:") { countObjects = it }
        inputValue("Enter count of boxes:") { countBox = it }
        inputValue("Enter size of box:") { boxSize = it }

        if (countObjects > 0) {
            println("Please enter each size of object")
            for (index in 1..countObjects) {
                inputValue("Enter size of object #$index:") { listObjects.add(it) }
            }
        }

        println("Calculate algorithm\nPlease wait....")
        fillQueueBox()
        calculateCountInBox()
    }

    private fun fillQueueBox() {
        for (index in 0 until countBox) listBox.add(0)
    }

    private fun calculateCountInBox() {
        var countPackage = 0
        var countPacking = 0
        var countBoxFilled = 0
        var lastSizeListObject = 0
        loopBox@ for (box in 0 until countBox) {
            var offset = 0
            if ((listObjects.size == 1 && listObjects[0] > boxSize) || lastSizeListObject == countObjects) {
                break@loopBox
            } else {
                println("************** Box packing process number ${box + 1} *****************")
                println("List of Objects: $listObjects\n")
                loopObj@ for (indexObj in 0 until countObjects) {
                    val obj = listObjects[indexObj - offset]
                    lastSizeListObject = listObjects.size
                    countObjects = listObjects.size
                    when {
                        isSizeObjInBox(listBox[box], obj) -> {
                            countPackage++
                            listBox[box] += obj
                            listObjects.removeAt(indexObj - offset)
                            offset++
                            if (isFillBox(listBox[box]) || listObjects.size == 1) {
                                countObjects = listObjects.size
                                countBoxFilled++
                                break@loopObj
                            } else {
                                countPacking++
                            }
                        }
                    }
                }
            }
        }
        if (countBoxFilled < countBox) {
            if (countPacking != 0) {
                println("$countPacking boxes are not filled.")
            }
            println("${countBox - countBoxFilled} boxes are empty.\n")
        }
        footer(countPackage)
    }

    private fun isSizeObjInBox(box: Int, obj: Int): Boolean = boxSize - box >= obj
    private fun isFillBox(box: Int): Boolean = boxSize - box == 0

    private fun inputValue(
        msg: String,
        mustInputLine: Boolean = true,
        mustPrintLn: Boolean = false,
        msgFailed: String = "Your input value is wrong!\nPlease retry.",
        action: (Int) -> Unit
    ) {
        if (mustPrintLn) println(msg) else print(msg)
        if (mustInputLine) readLine()?.takeIf { it.isNumeric() }?.let { action(it.toInt()) } ?: println(msgFailed)
    }

}