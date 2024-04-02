package com.example.unitconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconvertor.ui.theme.UnitCOnvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitCOnvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                    color = MaterialTheme.colorScheme.background
                ) {
                        UnitConvertor()
                }
            }
        }
    }
}

@Composable
fun  UnitConvertor()
{
    var inputValue by remember{ mutableStateOf("")}
    var outputValue by remember {mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Centimeters") }
    var outputUnit by remember {mutableStateOf("Meters")}
    var iExpanded by remember{ mutableStateOf(false)}
    var oExpanded by remember{ mutableStateOf(false)}
    val conversionFactor = remember{ mutableStateOf(0.01) }
    val oConversionFactor = remember{ mutableStateOf(1.00) }
    fun convertUnit()
    {

        val input = inputValue.toDoubleOrNull() ?: 0.0
        val result = (input * conversionFactor.value* 100.0/oConversionFactor.value ).roundToInt()/100.0
        outputValue = result.toString()
    }
    fun inputDropDownDetails(inputValue : String,inputConversionFactor : Double)
    {
        iExpanded = false
        inputUnit = inputValue
        conversionFactor.value = inputConversionFactor
        convertUnit()
    }
    fun outputDropDownDetails(outputValue : String,outputConversionFactor : Double)
    {
        oExpanded = false
        outputUnit = outputValue
        oConversionFactor.value = outputConversionFactor
        convertUnit()
    }

    Column(

        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text ="Unit Converter", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            placeholder = {Text("Enter Value")},onValueChange = { inputValue = it
                convertUnit()},)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Input Button
              Box{

                  Button(onClick = {
                      iExpanded = !iExpanded

                  }) {
                      Text(text = inputUnit)
                      Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down")
                  }
                  DropdownMenu(expanded = iExpanded , onDismissRequest = { iExpanded = false

                  }) {
                    DropdownMenuItem(text = { Text("Centimeter") }, onClick = {
                      inputDropDownDetails("Centimeter",0.1)

                    })
                      DropdownMenuItem(text = { Text("Meters") }, onClick = {
                          inputDropDownDetails("Meters",1.0)

                      })
                      DropdownMenuItem(text = { Text("Feet") }, onClick = {
                          inputDropDownDetails("Feet",0.3048)

                      })
                      DropdownMenuItem(text = { Text("Milimeters") }, onClick = {
                          inputDropDownDetails("Milimeters",0.001)

                      })
                  }
                 }
            Spacer(modifier = Modifier.width(16.dp))
              Box{
                  Button(onClick = {
                      oExpanded = !oExpanded
                  }) {
                      Text(text = outputUnit)
                      Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down")
                  }
                  DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false

                  }) {
                      DropdownMenuItem(text = { Text("Centimeter") }, onClick = {
                          outputDropDownDetails("Centimeter",0.1)

                      })
                      DropdownMenuItem(text = { Text("Meters") }, onClick = {
                          outputDropDownDetails("Meters",1.0)
                      })
                      DropdownMenuItem(text = { Text("Feet") }, onClick = {
                          outputDropDownDetails("Feet",0.3048)

                      })
                      DropdownMenuItem(text = { Text("Milimeters") }, onClick = {
                          outputDropDownDetails("Milimeters",0.001)
                      })
                  }

              }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outputValue $outputUnit", textAlign = TextAlign.Center)
    }
}




@Preview(showBackground = true)
@Composable
fun UnitConvertorPreview()
{
    UnitConvertor()
}