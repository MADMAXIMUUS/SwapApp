package com.example.swap.homescreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swap.R
import com.example.swap.models.BillModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    mode: Boolean,
    bills: List<BillModel>
) {
    if (!mode) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(bills.size) { id ->
                HomeSmallItem(bill = bills[id])

            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(bills.size) { id ->
                HomeLineItem(bill = bills[id])
            }
        }
    }
}

@Preview
@Composable
fun ItemPreview() {
    HomeLineItem(BillModel("10100", "Подушка", "Хоррошая подушка, надо брать"))
}

@Composable
fun HomeSmallItem(bill: BillModel) {
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .offset(0.dp,10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_owl_search),
                contentDescription = stringResource(R.string.product_photo),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(6.dp))
                    .height(90.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = bill.title,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.offset(10.dp, 5.dp)
            )
            Text(
                text = bill.description,
                Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
fun HomeLineItem(bill: BillModel) {
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
    ) {
        Row(modifier = Modifier.height(100.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_owl_search),
                contentDescription = stringResource(R.string.product_photo),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.height(110.dp).width(110.dp).offset(10.dp,10.dp)
            )
            Column(modifier = Modifier.offset(20.dp,20.dp)) {
                Text(
                    text = bill.title,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.offset(10.dp, 0.dp)
                )
                Text(
                    text = bill.description,
                    Modifier.padding(10.dp, 0.dp, 10.dp, 10.dp),
                    style = MaterialTheme.typography.subtitle1
                )
            }

        }
    }
}