package com.opera.core.systems.scope.services.ums;

import java.awt.Point;

import org.openqa.selenium.RenderedWebElement;

import com.opera.core.systems.QuickWidget;
import com.opera.core.systems.ScopeServices;
import com.opera.core.systems.scope.AbstractService;
import com.opera.core.systems.scope.ExecCommand;
import com.opera.core.systems.scope.SystemInputCommand;
import com.opera.core.systems.scope.internal.OperaMouseKeys;
import com.opera.core.systems.scope.protos.ExecProtos.MouseAction;
import com.opera.core.systems.scope.protos.SystemInputProtos.KeyPressInfo;
import com.opera.core.systems.scope.protos.SystemInputProtos.MouseInfo;
import com.opera.core.systems.scope.services.SystemInput;
import com.opera.core.systems.scope.internal.OperaKeys;

public class SystemInputManager extends AbstractService implements SystemInput {

	 SystemInputManager(ScopeServices services, String version) {
			super(services, version);
			
			String serviceName = "system-input";
			
			if(!isVersionInRange(version, "5.0", serviceName))
				throw new UnsupportedOperationException(serviceName + " version " + version + " is not supported");
			
			services.setSystemInputManager(this);
		}
	
	public void click(Point location, int button, int num_clicks, int modifier) {
		MouseInfo.Builder actionBuilder = MouseInfo.newBuilder();
		actionBuilder.setX(location.x);
		actionBuilder.setY(location.y);
		actionBuilder.setButton(button); // MOUSE_BUTTON_1
		actionBuilder.setNumClicks(num_clicks);
		actionBuilder.setModifier(modifier);
		executeCommand(SystemInputCommand.CLICK, actionBuilder.clone());
	}
	
	public void mouseMove(Point location, int button, int modifier) {
		MouseInfo.Builder actionBuilder = MouseInfo.newBuilder();
		actionBuilder.setX(location.x);
		actionBuilder.setButton(button); 
		actionBuilder.setNumClicks(1); // NOT RELEVANT- SHOULD BE OPTIONAL?
		actionBuilder.setModifier(modifier);
		executeCommand(SystemInputCommand.MOUSEMOVE, actionBuilder.clone());
	}
	
	public void mouseUp(Point location, int button, int modifier) {
		MouseInfo.Builder actionBuilder = MouseInfo.newBuilder();
		actionBuilder.setX(location.x);
		actionBuilder.setButton(button); 
		actionBuilder.setNumClicks(1); // NOT RELEVANT - SHOULD BE OPTIONAL?
		actionBuilder.setModifier(modifier);
		executeCommand(SystemInputCommand.MOUSEUP, actionBuilder.clone());
	}

	public void mouseDown(Point location, int button, int modifier) {
		MouseInfo.Builder actionBuilder = MouseInfo.newBuilder();
		actionBuilder.setX(location.x);
		actionBuilder.setButton(button); 
		actionBuilder.setNumClicks(1); // NOT RELEVANT - SHOULD BE OPTIONAL?
		actionBuilder.setModifier(modifier);
		executeCommand(SystemInputCommand.MOUSEDOWN, actionBuilder.clone());
	}
	
	public void keyPress(String key, int modifier) {
		String myKey = OperaKeys.get(key);
		System.out.println("myKey = " + myKey);
		KeyPressInfo.Builder actionBuilder = KeyPressInfo.newBuilder();
		actionBuilder.setKey(key);
		actionBuilder.setModifier(modifier);
		executeCommand(SystemInputCommand.KEYPRESS, actionBuilder.clone());
	}
	
}