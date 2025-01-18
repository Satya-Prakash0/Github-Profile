package com.satya.profilesearchapp.presentation.webview

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.satya.profilesearchapp.R
import com.satya.profilesearchapp.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {

    lateinit var binding: FragmentWebViewBinding
    private val args: WebViewFragmentArgs by navArgs()
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        webView = binding.webView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(args.repoUrl)

            // Handle page loading
            webView.webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                        progressBar.visibility = View.VISIBLE
                        webView.visibility = View.GONE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                        progressBar.visibility = View.GONE
                        webView.visibility = View.VISIBLE
                }

                override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                    super.onReceivedError(view, request, error)
                    binding.progressBar.visibility = View.GONE
                    showError("Error loading page")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Handle back navigation within WebView
        binding.webView.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                webView.goBack()
                true
            } else {
                false
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}