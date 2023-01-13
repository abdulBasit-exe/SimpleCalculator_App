package learning.appdev.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {
    var lastNumeric = false
    var lastDecimal = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    private fun removeZeroAfterDec(result: String): String{
        var value= result

        if (result.contains(".0")){
            value = result.substring(0,result.length-2)
        }

        return value;

    }
    
    // function for displaying digits, which are taken as input.
    fun onDigit(view: View){
        // when u cant import textView:
        val tvInput = findViewById<View>(R.id.tvInput) as TextView
        tvInput.append((view as Button).text)
        lastNumeric=true
    }
    // function for CLR operator
    fun onClear(view: View){
        val tvInput = findViewById<View>(R.id.tvInput) as TextView
        tvInput.setText("")
        lastNumeric=false
        lastDecimal=false
    }
    // function for showing decimal point
    fun onDecimal(view: View){
        val tvInput = findViewById<View>(R.id.tvInput) as TextView
        if (lastNumeric && !lastDecimal){
            tvInput.append(".")
            lastNumeric=false
            lastDecimal=true

        }
    }
    
    // function for calculation after the equal sign is tapped.
    fun onEqual(view: View){
        val tvInput = findViewById<View>(R.id.tvInput) as TextView
        if(lastNumeric){
            var tvValue = tvInput.text.toString()
            var prefix=""
            try {
                // to double check if first operand is -ve i.e -30-10
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                // for subtract operation
                if (tvValue.contains("-")){
                    // splitting the values before and after the operation.
                        // 99+1, 99, 1
                    var splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()){
                        one= prefix+one
                    }

                    tvInput.text = removeZeroAfterDec((one.toDouble() - two.toDouble()).toString())
                }
                
                // for devide operation
                else if (tvValue.contains("/")){

                    var splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()){
                        one= prefix+one
                    }

                    tvInput.text = removeZeroAfterDec((one.toDouble() / two.toDouble()).toString())
                }
                
                // for multiplication operation
                else if (tvValue.contains("*")){

                    var splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()){
                        one= prefix+one
                    }

                    tvInput.text = removeZeroAfterDec((one.toDouble() * two.toDouble()).toString())
                }
                
                // for addition operation
                else if (tvValue.contains("+")){

                    var splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()){
                        one= prefix+one
                    }

                    tvInput.text = removeZeroAfterDec((one.toDouble() + two.toDouble()).toString())
                }
            }
            catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onOperator(view: View){
        val tvInput = findViewById<View>(R.id.tvInput) as TextView
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric=false
            lastDecimal=false
        }
    }
    
    

    private fun isOperatorAdded(value: String):Boolean{
        return if (value.startsWith("-")){
            false
        }

        else{
            value.contains("/") || value.contains("*")
                    || value.contains("+") || value.contains("-")
        }


    }
}
