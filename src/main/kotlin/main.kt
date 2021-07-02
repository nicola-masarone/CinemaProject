
fun printStatistics(purTick: Int, totSeats: Int, curInc: Int, totInc: Int) {
    println()
    println("Number of purchased tickets: $purTick")
    println("Percentage: ${"%.2f".format(purTick.toDouble() / totSeats.toDouble() * 100.0)}%".replace(",", "."))
    println("Current income: $$curInc")
    println("Total income: $$totInc")
}

fun printCinema(myCinema: Array<CharArray>) {
    println()
    println("Cinema:")
    for (row in myCinema) {
        for (seat in row) {
            print("${seat} ")
        }
        println()
    }
    println()
}

fun buyATicket(seatsMax: Int, rowsMax: Int, myCinema: Array<CharArray>): Int {
    var purchaseOK = false
    var rowNum = 0
    var seatNum = 0
    var ticketPrice = 0

    do {
        println()
        println("Enter a row number:")
        rowNum = readLine()!!.toInt()
        println("Enter a seat number in that row:")
        seatNum = readLine()!!.toInt()
        if (rowNum < 1 || rowNum > rowsMax || seatNum < 0 || seatNum > seatsMax) {
            println()
            println("Wrong input!")
        } else if (myCinema[rowNum][seatNum] == 'B') {
            println()
            println("That ticket has already been purchased!")
        } else {
            purchaseOK = true
            ticketPrice = if (seatsMax * rowsMax <= 60) { 10 }
            else if (rowNum <= rowsMax / 2) { 10 }
            else { 8 }
            println()
            println("Ticket price: $$ticketPrice")
            myCinema[rowNum][seatNum] = 'B'
        }
    } while (!purchaseOK)

    return ticketPrice
}

fun main(args: Array<String>) {
    var purchasedTickets = 0
    var currentIncome = 0

    println("Enter the number of rows:")
    val rows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val seatsPerRow = readLine()!!.toInt()
    val totalSeats = rows * seatsPerRow
    val totalIncome = if (totalSeats <= 60) { totalSeats * 10 }
    else { rows / 2 * seatsPerRow * 10 + (rows - rows / 2) * seatsPerRow * 8 }

    val cinema = Array(rows + 1, {CharArray(seatsPerRow + 1){'S'}})
    cinema.first()[0] = ' '
    for (i in 1..cinema.first().lastIndex) {
        cinema.first()[i] = i.toString()[0]
    }
    for (i in 1..cinema.lastIndex) {
        cinema[i][0] = i.toString()[0]
    }

    while(true) {
        println()
        println("""
            1. Show the seats
            2. Buy a ticket
            3. Statistics
            0. Exit
        """.trimIndent())

        val sel = readLine()!!.toInt()
        when(sel) {
            1 -> printCinema(cinema)
            2 -> {
                currentIncome += buyATicket(seatsPerRow, rows, cinema)
                purchasedTickets++
            }
            3 -> printStatistics(purchasedTickets, totalSeats, currentIncome, totalIncome)
            0 -> return
        }
    }

}