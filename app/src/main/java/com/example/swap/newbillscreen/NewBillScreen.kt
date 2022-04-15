package com.example.swap.newbillscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.swap.R
import com.example.swap.models.BillModel
import com.example.swap.ui.theme.Light_brown


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewBillScreen(drafts: List<BillModel>) {
    if (drafts.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.drafts),
                modifier = Modifier
                    .offset(20.dp, 3.dp)
                    .padding(2.dp),
                style = MaterialTheme.typography.h3
            )
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f),
            ) {
                items(drafts.size) { id ->
                    DraftItem(bill = drafts[id])
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults
                        .buttonColors(
                            backgroundColor = Light_brown
                        ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                ) {
                    Text(text = stringResource(R.string.create_bill_button))
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.drafts),
                modifier = Modifier
                    .offset(20.dp, 3.dp)
                    .padding(2.dp),
                style = MaterialTheme.typography.h3
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.7f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.dont_have_drafts_message),
                    style = MaterialTheme.typography.h1
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults
                        .buttonColors(
                            backgroundColor = Light_brown
                        ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                ) {
                    Text(text = stringResource(R.string.create_bill_button),
                    style = MaterialTheme.typography.h2)
                }
            }
        }
    }
}

@Composable
fun DraftItem(bill: BillModel) {
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
                .offset(0.dp, 10.dp)
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