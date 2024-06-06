package com.example.stacklab

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.text.TextPaint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.stacklab.databinding.FragmentReminderBinding


class ReminderFragment : Fragment() {

    private lateinit var binding: FragmentReminderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReminderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isNotificationsEnabled()
        setProTextGradient()

    }

    private fun setProTextGradient() {

        val textViewProInMessage = binding.textViewProInMessage

        val paint: TextPaint = textViewProInMessage.paint
        val width = paint.measureText(textViewProInMessage.text.toString())

        val textProInMessageShader: Shader = LinearGradient(
            0f, 0f, width, textViewProInMessage.textSize, intArrayOf(
                Color.parseColor("#FFE259"),
                Color.parseColor("#FFA751"),
            ), null, Shader.TileMode.CLAMP
        )
        textViewProInMessage.paint.setShader(textProInMessageShader)


        val textViewProInNotification = binding.textViewProInNotification

        val textViewProInNotificationPaint: TextPaint = textViewProInNotification.paint
        val textViewProInNotificationPaintWidth =
            textViewProInNotificationPaint.measureText(textViewProInNotification.text.toString())

        val textProInNotificationShader: Shader = LinearGradient(
            0f,
            0f,
            textViewProInNotificationPaintWidth,
            textViewProInNotification.textSize,
            intArrayOf(
                Color.parseColor("#FFE259"),
                Color.parseColor("#FFA751"),
            ),
            null,
            Shader.TileMode.CLAMP
        )
        textViewProInNotification.paint.setShader(textProInNotificationShader)
    }

    private fun isNotificationsEnabled() {
        val notificationSwitch = binding.switchNotification

        val uiElementsToDisable = listOf(
            binding.imageViewNotificationStatus,
            binding.textViewSchedule,
            binding.textViewStart,
            binding.textViewStartDescription,
            binding.textViewEnd,
            binding.textViewEndDescription,
            binding.textViewInterval,
            binding.textViewIntervalDescription,
            binding.textViewMessage,
            binding.textViewCustomMessage,
            binding.textViewCustomMessageDescription,
            binding.textViewNotificationSound,
            binding.imageViewPlay,
            binding.textViewDefault,
            binding.textViewNotificationSoundDescription
        )

        val spinnerStart = binding.spinnerStart
        val spinnerEnd = binding.spinnerEnd
        val spinnerInterval = binding.spinnerInterval

        notificationSwitch.setOnClickListener {
            if (!notificationSwitch.isChecked) {
                binding.allowNotificationsLayout.visibility = View.VISIBLE

                uiElementsToDisable.forEach { uiElement ->
                    uiElement.isEnabled = false
                }
                spinnerStart.isEnabled = false
                spinnerEnd.isEnabled = false
                spinnerInterval.isEnabled = false

            } else {
                binding.allowNotificationsLayout.visibility = View.GONE

                uiElementsToDisable.forEach { uiElement ->
                    uiElement.isEnabled = true
                }
                spinnerStart.isEnabled = true
                spinnerEnd.isEnabled = true
                spinnerInterval.isEnabled = true

            }
        }
    }
}