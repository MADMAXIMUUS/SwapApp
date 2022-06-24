package com.example.swap.presentation.camerascreen

import android.content.Context
import android.net.Uri
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.swap.R
import com.example.swap.ui.theme.*
import com.example.swap.utilities.getDirectory
import com.example.swap.utilities.showToast
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CameraScreen(navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    SimpleCameraPreview(
        modifier = Modifier.fillMaxSize(),
        context = context,
        lifecycleOwner = lifecycleOwner,
        outputDirectory = getDirectory(context),
        onMediaCaptured = { url -> navController.popBackStack()}
    )
}

@Composable
fun SimpleCameraPreview(
    modifier: Modifier,
    context: Context,
    lifecycleOwner: LifecycleOwner,
    outputDirectory: File,
    onMediaCaptured: (Uri?) -> Unit
) {
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    var preview by remember { mutableStateOf<Preview?>(null) }
    val camera: Camera? = null
    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }
    var flashEnabled by remember { mutableStateOf(false) }
    var flashRes by remember { mutableStateOf(R.drawable.ic_flash_off) }
    val executor = ContextCompat.getMainExecutor(context)
    var cameraSelector: CameraSelector
    val cameraProvider = cameraProviderFuture.get()

    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = { ctx ->
                val previewView = PreviewView(ctx)
                cameraProviderFuture.addListener({
                    val imageAnalysis = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .apply {
                            setAnalyzer(executor, FaceAnalyzer())
                        }
                    imageCapture = ImageCapture.Builder()
                        .setTargetRotation(previewView.display.rotation)
                        .build()

                    cameraSelector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()

                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        imageCapture,
                        preview
                    )
                }, executor)
                preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                previewView
            }
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    color = if (isSystemInDarkTheme()) Dark_bottom_top_bar else White
                )
                .align(Alignment.BottomCenter)
        ) {
            Spacer(modifier = Modifier.width(0.dp))
            IconButton(onClick = {
                camera?.let {
                    if (it.cameraInfo.hasFlashUnit()) {
                        flashEnabled = !flashEnabled
                        flashRes = if (flashEnabled)
                            R.drawable.ic_flash_off
                        else
                            R.drawable.ic_flash_on
                        it.cameraControl.enableTorch(flashEnabled)
                    }
                }
            }) {
                Icon(
                    painter = painterResource(id = flashRes),
                    contentDescription = "Flashlight button",
                    modifier = Modifier.size(40.dp),
                    tint = if (isSystemInDarkTheme())
                        Light_brown
                    else
                        Night_blue
                )
            }
            Button(
                onClick = {
                    val imgCapture = imageCapture ?: return@Button
                    val photoFile = File(
                        outputDirectory,
                        SimpleDateFormat("yyyyMMDD-HHmmss", Locale.US)
                            .format(System.currentTimeMillis()) + ".jpg"
                    )
                    val outputFileOptions = ImageCapture
                        .OutputFileOptions
                        .Builder(photoFile)
                        .build()
                    imgCapture.takePicture(
                        outputFileOptions,
                        executor,
                        object : ImageCapture.OnImageSavedCallback {
                            override fun onImageSaved(
                                outputFileResults: ImageCapture.OutputFileResults
                            ) {
                                onMediaCaptured(Uri.fromFile(photoFile))
                            }

                            override fun onError(exception: ImageCaptureException) {
                                showToast("Something went wrong", context)
                            }

                        }
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

            IconButton(
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
            Spacer(modifier = Modifier.width(0.dp))
        }
    }
}
