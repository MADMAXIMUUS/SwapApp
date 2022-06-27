package com.example.swap.presentation.camerascreen

import android.content.Context
import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.swap.ui.theme.*
import com.example.swap.utilities.getDirectory
import com.skydoves.landscapist.glide.GlideImage
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun CameraScreen(navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    SimpleCameraPreview(
        modifier = Modifier.fillMaxSize(),
        context = context,
        lifecycleOwner = lifecycleOwner,
        outputDirectory = getDirectory(context),
        onMediaCaptured = { uris ->
            navController.popBackStack()
        },
        onError = {

        }
    )
}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider =
    suspendCoroutine { continuation ->
        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
            cameraProvider.addListener({
                continuation.resume(cameraProvider.get())
            }, ContextCompat.getMainExecutor(this))
        }
    }

private fun takePhoto(
    filenameFormat: String,
    imageCapture: ImageCapture,
    outputDirectory: File,
    executor: Executor,
    onMediaCaptured: (Uri) -> Unit,
    onError: (String) -> Unit
) {
    val photoFile = File(
        outputDirectory,
        SimpleDateFormat(filenameFormat, Locale.US).format(System.currentTimeMillis()) + ".jpg"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.takePicture(
        outputOptions,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(exception: ImageCaptureException) {
                onError(exception.localizedMessage ?: "Что-то пошло не так")
            }

            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = Uri.fromFile(photoFile)
                onMediaCaptured(savedUri)
            }
        }
    )
}

@Composable
fun SimpleCameraPreview(
    modifier: Modifier,
    context: Context,
    lifecycleOwner: LifecycleOwner,
    outputDirectory: File,
    onMediaCaptured: (MutableList<Uri>) -> Unit,
    onError: (String) -> Unit
) {
    val imgList = remember { mutableStateListOf<Uri>(Uri.EMPTY) }
    val showBar = remember {
        mutableStateOf(false)
    }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }
    val preview = Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    //val camera: Camera? = null
    val lensFacing = remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }
    /*val flashEnabled by remember { mutableStateOf(false) }
    val flashRes by remember { mutableStateOf(R.drawable.ic_flash_off) }*/
    val executor = Executors.newSingleThreadExecutor()
    val cameraSelector = CameraSelector
        .Builder()
        .requireLensFacing(lensFacing.value)
        .build()
    //val cameraProvider = cameraProviderFuture.get()

    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )

        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    Column(modifier = modifier) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            factory = { previewView }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(if (isSystemInDarkTheme()) Dark_bottom_top_bar else White)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                text = "Наведите камеру на объект и сфотографируйте его",
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
                color = if (isSystemInDarkTheme()) Yellow else Deep_dark_blue
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .weight(0.3f)
                .background(
                    color = if (isSystemInDarkTheme()) Dark_bottom_top_bar else White
                )
        ) {
            /*Spacer(modifier = Modifier.width(0.dp))
            IconButton(onClick = {
                *//*camera?.let {
                    if (it.cameraInfo.hasFlashUnit()) {
                        flashEnabled = !flashEnabled
                        flashRes = if (flashEnabled)
                            R.drawable.ic_flash_off
                        else
                            R.drawable.ic_flash_on
                        it.cameraControl.enableTorch(flashEnabled)
                    }
                }*//*
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_flash_on),
                    contentDescription = "Flashlight button",
                    modifier = Modifier.size(40.dp),
                    tint = if (isSystemInDarkTheme())
                        Light_brown
                    else
                        Night_blue
                )
            }*/
            Button(
                onClick = {
                    takePhoto(
                        filenameFormat = "yyyy-MM-dd-HH-mm-ss-SSS",
                        imageCapture = imageCapture,
                        outputDirectory = outputDirectory,
                        executor = executor,
                        onMediaCaptured = { uri ->
                            if (imgList.size == 1)
                                imgList[0] = uri
                            else
                                imgList.add(uri)
                            showBar.value = true
                        },
                        onError = onError
                    )
                },
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isSystemInDarkTheme())
                        Yellow
                    else
                        Deep_dark_blue
                ),
                content = {}
            )

            /*IconButton(
                onClick = {
                    lensFacing =
                        if (lensFacing == CameraSelector.LENS_FACING_BACK) CameraSelector.LENS_FACING_FRONT
                        else CameraSelector.LENS_FACING_BACK

                    cameraSelector = CameraSelector.Builder()
                        .requireLensFacing(lensFacing)
                        .build()
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        imageCapture,
                        preview
                    )
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera_revert),
                    contentDescription = "",
                    modifier = Modifier.size(40.dp),
                    tint = if (isSystemInDarkTheme())
                        Light_brown
                    else
                        Night_blue
                )
            }
            Spacer(modifier = Modifier.width(0.dp))*/
        }
        if (showBar.value) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(
                        if (isSystemInDarkTheme())
                            Dark_bottom_top_bar
                        else
                            White
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (isSystemInDarkTheme())
                                Dark_Content_Background
                            else
                                Color.LightGray
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    GlideImage(
                        modifier = Modifier
                            .size(80.dp, 70.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        imageModel = imgList[0],
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        modifier = Modifier
                            .weight(0.5f),
                        text = "1 фото",
                        style = MaterialTheme.typography.h1,
                        color = if (isSystemInDarkTheme()) Yellow else Deep_dark_blue
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {

                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp)),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Light_brown
                        ),
                        content = {
                            Text(
                                text = "Готово",
                                style = MaterialTheme.typography.button,
                                color = if (isSystemInDarkTheme()) Dark_Background else White
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}
