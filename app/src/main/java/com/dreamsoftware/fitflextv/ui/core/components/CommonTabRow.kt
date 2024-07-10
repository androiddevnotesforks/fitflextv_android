package com.dreamsoftware.fitflextv.ui.core.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Tab
import androidx.tv.material3.TabRow
import androidx.tv.material3.TabRowDefaults
import androidx.tv.material3.Text

@Composable
@OptIn(ExperimentalComposeUiApi::class)
fun CommonTabRow(
    @StringRes tabs: List<Int>,
    selectedTabIndex: Int,
    focusTabIndex: Int,
    onClick: (Int) -> Unit,
    onFocus: (Int) -> Unit,
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.focusRestorer(),
        indicator = { tabPositions, doesTabRowHaveFocus ->

            TabRowDefaults.PillIndicator(
                currentTabPosition = tabPositions[focusTabIndex],
                activeColor = MaterialTheme.colorScheme.onSurface,
                inactiveColor = Color.Transparent,
                doesTabRowHaveFocus = doesTabRowHaveFocus,
            )

            TabRowDefaults.PillIndicator(
                currentTabPosition = tabPositions[selectedTabIndex],
                doesTabRowHaveFocus = doesTabRowHaveFocus,
            )
        },
    ) {
        tabs.forEachIndexed { index, tab ->
            key(index) {
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { onClick(index) },
                    onFocus = { onFocus(index) }
                ) {
                    Text(
                        text = stringResource(id = tab),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}
